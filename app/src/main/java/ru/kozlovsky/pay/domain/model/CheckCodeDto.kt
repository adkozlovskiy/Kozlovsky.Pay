package ru.kozlovsky.pay.domain.model

data class CheckCodeDto(
    val key: String,
    val pin: String
)
