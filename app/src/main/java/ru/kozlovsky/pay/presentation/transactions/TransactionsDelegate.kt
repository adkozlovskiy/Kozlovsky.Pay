package ru.kozlovsky.pay.presentation.transactions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
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
            with(itemViewBinding) {
                itTvRecipient.text = item.account
                itTvTime.text = "от 25 апреля 2023г. 16:45"
                amount.text = item.amount.formatAsCurrency()
                if (item.incoming) {
                    itIvIcon.rotation = 0f
                    itIvIcon.setColorFilter(
                        ResourcesCompat.getColor(resources, R.color.green_light, context.theme)
                    )
                } else {
                    itIvIcon.rotation = 180f
                    itIvIcon.setColorFilter(
                        ResourcesCompat.getColor(resources, R.color.gray, context.theme)
                    )
                }
            }
        }
    }
}