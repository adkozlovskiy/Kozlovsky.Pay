package ru.kozlovsky.pay.data.model.dto

data class CustomerDto(
    val id: Long? = null,
    val phone: String,
    val name: String?,
    val src: ImageDto? = null,
    val accounts: List<AccountDto>? = null,
    val status: CustomerStatus,
) {
    fun isAvailable() = status == CustomerStatus.ACTIVE

    fun isBlocked() = status == CustomerStatus.BLOCKED
}

enum class CustomerStatus {
    ACTIVE, BLOCKED
}
