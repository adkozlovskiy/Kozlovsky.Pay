package ru.kozlovsky.pay.data.model.dto

data class AccountDto(
    val id: Long? = null,

    val balance: Double,

    val created: Long,

    val updated: Long,

    val customer: CustomerDto,

    val status: AccountStatus
)

enum class AccountStatus {
    MODERATION, ACTIVE, SUSPENDED, CLOSED
}
