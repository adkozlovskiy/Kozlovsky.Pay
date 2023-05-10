package ru.kozlovsky.pay.presentation.home

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.core.view.isVisible
import dagger.hilt.android.AndroidEntryPoint
import ru.kozlovsky.pay.R
import ru.kozlovsky.pay.core.BaseFragment
import ru.kozlovsky.pay.databinding.FragmentHomeBinding
import ru.kozlovsky.pay.domain.navigation.navigate
import ru.kozlovsky.pay.presentation.adapter.compositeAdapter
import ru.kozlovsky.pay.presentation.pager.AdjustingPageChangeCallback
import ru.kozlovsky.pay.util.ShakeListener
import ru.kozlovsky.pay.util.extension.collectOnLifecycle
import ru.kozlovsky.pay.util.extension.setupPagerAdapter
import ru.kozlovsky.pay.util.extension.updateOffscreenPageLimit

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {
    override val viewModelClass: Class<HomeViewModel>
        get() = HomeViewModel::class.java

    override fun getViewBinding(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    private val accountsAdapter = compositeAdapter {
        add(
            AccountDelegate(
                onShareClicked = {
                    // shareTextPlain(it.id.toString())
                }
            )
        ) { item, _ ->
            viewModel.navigateToQrFragment(item)
        }
    }

    private val recentRecipientAdapter = compositeAdapter {
        add(
            RecentRecipientDelegate()
        )
    }

    private var sensorManager: SensorManager? = null
    private var accelerometer: Sensor? = null

    private val shakeListener = ShakeListener(
        onShake = {
            viewModel.onShake()
        }
    )

    override fun configureView() {
        super.configureView()
        sensorManager = requireContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        with(binding) {
            fhItemPhoneTransaction.root.setOnClickListener {
                viewModel.navigate(R.id.transactionFragment)
            }

            fhAccountsPager.registerOnPageChangeCallback(
                AdjustingPageChangeCallback(fhAccountsPager, R.dimen.padding4)
            )
            fhAccountsPager.setupPagerAdapter(accountsAdapter)
            dotsIndicator.attachTo(fhAccountsPager)

            ftRvRecentRecipients.adapter = recentRecipientAdapter

            fhItemNumberTransaction.imtTitle.text = getString(R.string.make_number_transaction)
            fhItemNumberTransaction.imtIcon.setImageResource(R.drawable.ic_transaction_account)

            fhItemQrTransaction.imtTitle.text = getString(R.string.make_qr_transaction)
            fhItemQrTransaction.imtIcon.setImageResource(R.drawable.ic_scanner)

            fhItemQrTransaction.root.setOnClickListener {
                viewModel.navigate(R.id.scannerFragment)
            }

            fhItemNumberTransaction.root.setOnClickListener {
                viewModel.navigate(R.id.accountTransactionFragment)
            }
            qrBadge.root.setOnClickListener {
                viewModel.navigateToPrimaryAccountQr()
            }
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
        collectOnLifecycle(viewModel.recentRecipients) {
            binding.ftRvRecentRecipients.isVisible = it.isNotEmpty()
            recentRecipientAdapter.submitList(it)
        }
        collectOnLifecycle(viewModel.accounts) {
            accountsAdapter.submitList(it)
            binding.fhAccountsPager.updateOffscreenPageLimit(it)
        }
    }

    override fun onResume() {
        super.onResume()
        sensorManager?.registerListener(shakeListener, accelerometer, SensorManager.SENSOR_DELAY_UI)
    }

    override fun onPause() {
        sensorManager?.unregisterListener(shakeListener)
        super.onPause()
    }
}