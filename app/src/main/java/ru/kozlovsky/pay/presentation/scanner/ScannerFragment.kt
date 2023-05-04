package ru.kozlovsky.pay.presentation.scanner

import android.Manifest
import android.content.pm.PackageManager
import android.util.SparseArray
import android.view.SurfaceHolder
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.core.app.ActivityCompat
import androidx.core.util.isNotEmpty
import androidx.core.view.isVisible
import com.airbnb.lottie.LottieDrawable.INFINITE
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import dagger.hilt.android.AndroidEntryPoint
import ru.kozlovsky.pay.R
import ru.kozlovsky.pay.core.BaseFragment
import ru.kozlovsky.pay.databinding.FragmentScannerBinding
import ru.kozlovsky.pay.domain.navigation.navigate
import ru.kozlovsky.pay.domain.navigation.navigateUp
import ru.kozlovsky.pay.util.extension.collectOnLifecycle
import ru.kozlovsky.pay.util.extension.openSettings


@AndroidEntryPoint
class ScannerFragment : BaseFragment<ScannerViewModel, FragmentScannerBinding>() {

    override val viewModelClass: Class<ScannerViewModel>
        get() = ScannerViewModel::class.java

    override fun getViewBinding(): FragmentScannerBinding {
        return FragmentScannerBinding.inflate(layoutInflater)
    }

    private val requestPermissionLauncher = registerForActivityResult(RequestPermission()) { isGranted: Boolean ->
        viewModel.setDisclaimerVisible(!isGranted)
    }

    private var barcodeDetector: BarcodeDetector? = null

    private var cameraSource: CameraSource? = null

    @Suppress("deprecation")
    override fun configureView() {
        super.configureView()
        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        with(binding) {
            fsLottieView.setAnimation("scanner_placeholder.json")
            fsLottieView.repeatCount = INFINITE
            fsLottieView.speed = ANIMATION_SPEED
            fsLottieView.resumeAnimation()

            fsIvClose.setOnClickListener { viewModel.navigateUp() }

            fhTvGoToSettings.setOnClickListener { openSettings() }
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
        collectOnLifecycle(viewModel.disclaimerVisible) {
            binding.fsLlDisclaimer.isVisible = it
        }
        collectOnLifecycle(viewModel.foundAccount) {
            viewModel.navigate(R.id.recipientDialogFragment)
        }
    }

    @Suppress("deprecation")
    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        binding.fsLottieView.pauseAnimation()
    }

    private fun initialiseDetectorsAndSources() {
        barcodeDetector = BarcodeDetector.Builder(requireContext())
            .setBarcodeFormats(Barcode.ALL_FORMATS)
            .build()

        cameraSource = CameraSource.Builder(requireContext(), barcodeDetector)
            .setRequestedPreviewSize(CAMERA_RES_WIDTH, CAMERA_RES_HEIGHT)
            .setAutoFocusEnabled(true)
            .build()

        binding.fsSurface.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                if (ActivityCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.CAMERA
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    cameraSource?.start(binding.fsSurface.holder)
                } else {
                    requestPermissionLauncher.launch(Manifest.permission.CAMERA)
                }
            }

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}
            override fun surfaceDestroyed(holder: SurfaceHolder) {
                cameraSource?.stop()
            }
        })
        barcodeDetector?.setProcessor(object : Detector.Processor<Barcode?> {
            override fun release() {
                //
            }

            override fun receiveDetections(detections: Detector.Detections<Barcode?>) {
                val barcodes: SparseArray<Barcode?> = detections.detectedItems
                if (barcodes.isNotEmpty()) {
                    val barcode = barcodes.valueAt(0)
                    val account = barcode?.rawValue
                    viewModel.searchForAccount(account)
                }
            }
        })
    }

    override fun onPause() {
        super.onPause()
        cameraSource?.release()
    }

    override fun onResume() {
        super.onResume()
        initialiseDetectorsAndSources()
    }

    companion object {
        const val ANIMATION_SPEED = 1.8f

        const val CAMERA_RES_HEIGHT = 1080
        const val CAMERA_RES_WIDTH = 1920
    }
}