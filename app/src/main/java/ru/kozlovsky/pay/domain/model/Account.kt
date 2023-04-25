package ru.kozlovsky.pay.domain.model

import ru.kozlovsky.pay.data.model.dto.AccountStatus
import ru.kozlovsky.pay.data.model.dto.CustomerDto
import ru.kozlovsky.pay.presentation.adapter.ListItem

data class Account(
    val id: Long,
    val balance: Double,
    val created: Long,
    val updated: Long,
    val customer: CustomerDto?,
    val status: AccountStatus
) : ListItem() {
    override fun areItemsTheSame(other: ListItem) = id == (other as Account).id
}