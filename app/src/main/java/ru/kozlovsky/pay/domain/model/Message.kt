package ru.kozlovsky.pay.domain.model

import ru.kozlovsky.pay.presentation.adapter.ListItem

data class Message(
    val id: Long,
    val content: String,
    val mine: Boolean,
) : ListItem() {
    override fun areItemsTheSame(other: ListItem) = id == (other as? Message)?.id
}