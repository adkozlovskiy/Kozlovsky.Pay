package ru.kozlovsky.pay.presentation.support

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.kozlovsky.pay.R
import ru.kozlovsky.pay.databinding.ItemIncomingMessageBinding
import ru.kozlovsky.pay.domain.model.Message
import ru.kozlovsky.pay.presentation.adapter.BaseDelegate
import ru.kozlovsky.pay.presentation.adapter.BaseViewHolder
import ru.kozlovsky.pay.presentation.adapter.ListItem

class SupportIncomingMessageDelegate : BaseDelegate<Message, ItemIncomingMessageBinding>() {
    override fun isRelativeItem(item: ListItem) = item is Message && !item.mine

    override fun getLayoutId() = R.layout.item_incoming_message

    override fun getViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<ItemIncomingMessageBinding, Message> {
        return SupportMessageViewHolder(
            ItemIncomingMessageBinding.inflate(inflater, parent, false)
        )
    }

    inner class SupportMessageViewHolder(binding: ItemIncomingMessageBinding) :
        BaseViewHolder<ItemIncomingMessageBinding, Message>(binding) {
        override fun onBind(item: Message) {
            itemViewBinding.content.text = item.content
        }
    }
}