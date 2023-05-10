package ru.kozlovsky.pay.presentation.transaction

import androidx.core.view.isVisible
import dagger.hilt.android.AndroidEntryPoint
import ru.kozlovsky.pay.R
import ru.kozlovsky.pay.core.BaseBottomSheetDialog
import ru.kozlovsky.pay.databinding.DialogTransactionsBinding
import ru.kozlovsky.pay.domain.navigation.navigate

@AndroidEntryPoint
class TransactionsBottomDialog : BaseBottomSheetDialog<TransactionsViewModel, DialogTransactionsBinding>() {
    override val viewModelClass: Class<TransactionsViewModel>
        get() = TransactionsViewModel::class.java

    override fun getViewBinding(): DialogTransactionsBinding {
        return DialogTransactionsBinding.inflate(layoutInflater)
    }

    override fun configureView() {
        super.configureView()
        with(binding) {
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

            fhItemPhoneTransaction.root.setOnClickListener {
                viewModel.navigate(R.id.transactionFragment)
            }

            fhItemPhoneTransaction.imtArrow.isVisible = false
            fhItemNumberTransaction.imtArrow.isVisible = false
            fhItemQrTransaction.imtArrow.isVisible = false

            close.setOnClickListener {
                dismiss()
            }
        }
    }

}