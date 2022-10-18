package ru.kozlovsky.pay.domain.model

data class Profile(
    val firstname: String? = null,
    val lastname: String? = null,
    val patronymic: String? = null,

    val avatarUrl: String? = null,
    val account: String? = null,
    val phoneNumber: String? = null
)
