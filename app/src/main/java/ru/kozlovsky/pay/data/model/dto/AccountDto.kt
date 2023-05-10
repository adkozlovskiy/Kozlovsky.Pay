package ru.kozlovsky.pay.data.model.dto

import ru.kozlovsky.pay.domain.model.Account

data class AccountDto(
    val id: Long? = null,

    val balance: Double,

    val created: Long,

    val updated: Long,

    val customer: CustomerDto,

    val status: AccountStatus
)

fun AccountDto.toAccount() = Account(id!!, balance, false, status)

enum class AccountStatus {
    MODERATION, ACTIVE, SUSPENDED, CLOSED
}
