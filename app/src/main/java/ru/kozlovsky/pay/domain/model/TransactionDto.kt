package ru.kozlovsky.pay.domain.model

import ru.kozlovsky.pay.presentation.adapter.ListItem

data class Transaction(
    val id: Long,
    val time: Long,
    val amount: Double,
    val account: String,
    val incoming: Boolean
//    val fromAccount: Account,
//    val toAccount: Account,
//    val status: TransactionStatus
) : ListItem() {
    override fun areItemsTheSame(other: ListItem) = id == (other as? Transaction)?.id
}