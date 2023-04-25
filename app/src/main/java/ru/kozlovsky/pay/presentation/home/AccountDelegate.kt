package ru.kozlovsky.pay.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.kozlovsky.pay.R
import ru.kozlovsky.pay.databinding.ItemAccountBinding
import ru.kozlovsky.pay.domain.model.Account
import ru.kozlovsky.pay.presentation.adapter.BaseDelegate
import ru.kozlovsky.pay.presentation.adapter.BaseViewHolder
import ru.kozlovsky.pay.presentation.adapter.ListItem

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
            itemViewBinding.balance.text = "${item.balance} Р."
            itemViewBinding.number.text = item.id.toString()

            itemViewBinding.root.setOnClickListener {
                onItemClickListener?.invoke(item, adapterPosition)
            }
            itemViewBinding.share.setOnClickListener {
                onShareClicked.invoke(item)
            }
        }
    }
}