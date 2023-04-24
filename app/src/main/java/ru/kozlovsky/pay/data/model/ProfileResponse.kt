package ru.kozlovsky.pay.data.model

data class ProfileResponse(
    val profileId: Long? = null,
    val firstname: String? = null,
    val lastname: String? = null,
    val patronymic: String? = null,
    val avatarUrl: String? = null,
    val account: String? = null,
    val phoneNumber: String? = null
)