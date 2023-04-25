package ru.kozlovsky.pay.domain.model

data class RequestCodeResponse(
    val code: String,
    val type: RequestCodeType
)

enum class RequestCodeType {
    REGISTRATION, AUTHORIZATION
}
