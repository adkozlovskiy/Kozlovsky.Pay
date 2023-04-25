package ru.kozlovsky.pay.domain.model

data class RecoveryPasswordRequest(
    val phone: String,
    val oldPassword: String,
    val newPassword: String
)