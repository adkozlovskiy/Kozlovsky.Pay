package ru.kozlovsky.pay.presentation.adapter

import androidx.recyclerview.widget.DiffUtil

/**
 * [DiffUtil.ItemCallback] для [ListItem]
 */
open class CompositeDiffCallback : DiffUtil.ItemCallback<ListItem>() {

    override fun getChangePayload(oldItem: ListItem, newItem: ListItem): Any? {
        return oldItem.payload(newItem)
    }

    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem::class == newItem::class && oldItem.areItemsTheSame(newItem)
    }

    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem::class == newItem::class && oldItem.areContentsTheSame(newItem)
    }
}
