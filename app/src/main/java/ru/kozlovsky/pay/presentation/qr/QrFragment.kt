package ru.kozlovsky.pay.presentation.qr

import dagger.hilt.android.AndroidEntryPoint
import ru.kozlovsky.pay.R
import ru.kozlovsky.pay.core.BaseBottomSheetDialog
import ru.kozlovsky.pay.core.BaseFragment
import ru.kozlovsky.pay.databinding.FragmentQrBinding
import ru.kozlovsky.pay.domain.navigation.navigateUp
import ru.kozlovsky.pay.util.extension.collectOnLifecycle

@AndroidEntryPoint
class QrFragment : BaseBottomSheetDialog<QrViewModel, FragmentQrBinding>() {
    override val viewModelClass: Class<QrViewModel>
        get() = QrViewModel::class.java

    override fun getViewBinding(): FragmentQrBinding {
        return FragmentQrBinding.inflate(layoutInflater)
    }

    override fun configureView() {
        super.configureView()
        binding.close.setOnClickListener {
            viewModel.navigateUp()
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
        collectOnLifecycle(viewModel.qrCode) {
            binding.qr.setImageBitmap(it)
        }

        collectOnLifecycle(viewModel.account) {
            binding.accNumber.text = getString(R.string.account_number_placeholder, it.formattedNumber)
        }
    }
}