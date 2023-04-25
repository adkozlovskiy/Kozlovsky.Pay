package ru.kozlovsky.pay.data.model.dto

data class TransactionDto(

    val id: Long? = null,

    val time: Long,

    val amount: Double,

    val fromAccount: AccountDto,

    val toAccount: AccountDto,

    val status: TransactionStatus
)

enum class TransactionStatus {
    SUSPENDED, DONE, CANCELED
}