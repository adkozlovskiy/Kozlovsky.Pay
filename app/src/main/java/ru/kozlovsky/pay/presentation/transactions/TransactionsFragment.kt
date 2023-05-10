package ru.kozlovsky.pay.presentation.transactions

import dagger.hilt.android.AndroidEntryPoint
import ru.kozlovsky.pay.core.BaseFragment
import ru.kozlovsky.pay.databinding.FragmentTransactionsBinding
import ru.kozlovsky.pay.domain.model.Transaction
import ru.kozlovsky.pay.presentation.adapter.compositeAdapter

@AndroidEntryPoint
class TransactionsFragment : BaseFragment<TransactionsViewModel, FragmentTransactionsBinding>() {

    private val transactionsAdapter = compositeAdapter {
        add(
            TransactionsDelegate()
        ) { item, position ->

        }
    }

    override val viewModelClass: Class<TransactionsViewModel>
        get() = TransactionsViewModel::class.java

    override fun getViewBinding(): FragmentTransactionsBinding {
        return FragmentTransactionsBinding.inflate(layoutInflater)
    }

    override fun configureView() {
        super.configureView()
        binding.ftRecycler.adapter = transactionsAdapter
        transactionsAdapter.submitList(
            listOf(
                Transaction(1, 12312312, 130.41, "Максим К.", true),
                Transaction(2, 12312312, 300.00, "Теплов А.", false),
            )
        )
    }

}