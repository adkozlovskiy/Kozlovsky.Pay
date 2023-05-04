package ru.kozlovsky.pay.presentation.scanner.recipient

import android.content.DialogInterface
import dagger.hilt.android.AndroidEntryPoint
import ru.kozlovsky.pay.core.BaseBottomSheetDialog
import ru.kozlovsky.pay.databinding.DialogQrRecipientBinding
import ru.kozlovsky.pay.domain.navigation.setResult
import ru.kozlovsky.pay.presentation.scanner.ScannerViewModel

@AndroidEntryPoint
class RecipientDialogFragment : BaseBottomSheetDialog<ScannerViewModel, DialogQrRecipientBinding>() {
    override val viewModelClass: Class<ScannerViewModel>
        get() = ScannerViewModel::class.java

    override fun getViewBinding(): DialogQrRecipientBinding {
        return DialogQrRecipientBinding.inflate(layoutInflater)
    }

    override fun configureView() {
        super.configureView()
        binding.close.setOnClickListener {
            dismiss()
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        viewModel.setResult(true)
    }
}