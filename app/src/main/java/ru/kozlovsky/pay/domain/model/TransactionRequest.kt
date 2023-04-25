package ru.kozlovsky.pay.domain.model

data class TransactionRequest(
    val value: String,
    val amount: Double,
    val fromAccountId: Long,
    val type: TransactionType,
)

enum class TransactionType {
    PHONE, ACCOUNT
}
