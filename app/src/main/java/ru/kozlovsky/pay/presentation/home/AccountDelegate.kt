package ru.kozlovsky.pay.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.kozlovsky.pay.R
import ru.kozlovsky.pay.databinding.ItemAccountBinding
import ru.kozlovsky.pay.domain.model.Account
import ru.kozlovsky.pay.presentation.adapter.BaseDelegate
import ru.kozlovsky.pay.presentation.adapter.BaseViewHolder
import ru.kozlovsky.pay.presentation.adapter.ListItem
import ru.kozlovsky.pay.util.Constants.BALANCE_HIDDEN_VALUE
import ru.kozlovsky.pay.util.extension.formatAsCurrency
import ru.kozlovsky.pay.util.extension.getMaskedText
import java.text.NumberFormat
import java.util.Currency

class AccountDelegate(
    private val onShareClicked: (Account) -> Unit,
) : BaseDelegate<Account, ItemAccountBinding>() {
    override fun isRelativeItem(item: ListItem): Boolean = item is Account

    override fun getLayoutId(): Int {
        return R.layout.item_account
    }

    override fun getViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<ItemAccountBinding, Account> {
        return AccountViewHolder(
            ItemAccountBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    inner class AccountViewHolder(binding: ItemAccountBinding) : BaseViewHolder<ItemAccountBinding, Account>(binding) {
        override fun onBind(item: Account) {
            if (item.balanceHidden) {
                itemViewBinding.balance.text = BALANCE_HIDDEN_VALUE
            } else {
                itemViewBinding.balance.text = item.balance.formatAsCurrency()
            }

            itemViewBinding.number.text = "Основной счет"

            itemViewBinding.root.setOnClickListener {
                onItemClickListener?.invoke(item, adapterPosition)
            }
            itemViewBinding.share.setOnClickListener {
                onShareClicked.invoke(item)
            }
        }
    }
}