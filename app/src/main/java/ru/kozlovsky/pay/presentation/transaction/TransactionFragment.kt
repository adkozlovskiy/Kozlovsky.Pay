package ru.kozlovsky.pay.presentation.transaction

import dagger.hilt.android.AndroidEntryPoint
import ru.kozlovsky.pay.core.BaseFragment
import ru.kozlovsky.pay.databinding.FragmentTransactionBinding

@AndroidEntryPoint
class TransactionFragment : BaseFragment<TransactionViewModel, FragmentTransactionBinding>() {
    override val viewModelClass: Class<TransactionViewModel>
        get() = TransactionViewModel::class.java

    override fun getViewBinding(): FragmentTransactionBinding {
        return FragmentTransactionBinding.inflate(layoutInflater)
    }
}