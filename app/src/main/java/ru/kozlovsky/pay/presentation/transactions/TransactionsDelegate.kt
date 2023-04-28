package ru.kozlovsky.pay.presentation.transactions

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.kozlovsky.pay.R
import ru.kozlovsky.pay.databinding.ItemTransactionBinding
import ru.kozlovsky.pay.domain.model.Transaction
import ru.kozlovsky.pay.presentation.adapter.BaseDelegate
import ru.kozlovsky.pay.presentation.adapter.BaseViewHolder
import ru.kozlovsky.pay.presentation.adapter.ListItem
import ru.kozlovsky.pay.util.extension.formatAsCurrency

class TransactionsDelegate : BaseDelegate<Transaction, ItemTransactionBinding>() {
    override fun isRelativeItem(item: ListItem) = item is Transaction

    override fun getLayoutId(): Int {
        return R.layout.item_transaction
    }

    override fun getViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<ItemTransactionBinding, Transaction> {
        return TransactionViewHolder(
            ItemTransactionBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    inner class TransactionViewHolder(binding: ItemTransactionBinding) :
        BaseViewHolder<ItemTransactionBinding, Transaction>(binding) {
        override fun onBind(item: Transaction) {
            itemViewBinding.itTvRecipient.text = "Жопа"// todo
            itemViewBinding.itTvTime.text = "25 апреля 2023г."
            itemViewBinding.amount.text = item.amount.formatAsCurrency()
        }
    }
}