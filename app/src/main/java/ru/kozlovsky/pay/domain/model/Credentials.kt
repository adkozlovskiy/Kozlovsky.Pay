package ru.kozlovsky.pay.domain.model

data class Credentials(
    val phone: String,
    val password: String,
    val name: String?
)
