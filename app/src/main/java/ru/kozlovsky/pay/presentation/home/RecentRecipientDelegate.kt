package ru.kozlovsky.pay.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import ru.kozlovsky.pay.R
import ru.kozlovsky.pay.databinding.ItemRecentRecipientBinding
import ru.kozlovsky.pay.domain.model.RecentRecipient
import ru.kozlovsky.pay.presentation.adapter.BaseDelegate
import ru.kozlovsky.pay.presentation.adapter.BaseViewHolder
import ru.kozlovsky.pay.presentation.adapter.ListItem

class RecentRecipientDelegate : BaseDelegate<RecentRecipient, ItemRecentRecipientBinding>() {
    override fun isRelativeItem(item: ListItem): Boolean = item is RecentRecipient

    override fun getLayoutId(): Int {
        return R.layout.item_recent_recipient
    }

    override fun getViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<ItemRecentRecipientBinding, RecentRecipient> {
        return RecentRecipientViewHolder(
            ItemRecentRecipientBinding.inflate(inflater, parent, false)
        )
    }

    inner class RecentRecipientViewHolder(binding: ItemRecentRecipientBinding) :
        BaseViewHolder<ItemRecentRecipientBinding, RecentRecipient>(binding) {
        override fun onBind(item: RecentRecipient) {
            itemViewBinding.irrName.text = item.customer
            Glide.with(context).load(item.customerImage).into(itemViewBinding.irrSrc)
        }
    }
}