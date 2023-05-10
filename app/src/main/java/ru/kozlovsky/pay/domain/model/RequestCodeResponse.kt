package ru.kozlovsky.pay.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RequestCodeResponse(
    val key: String,
    val type: RequestCodeType
) : Parcelable

enum class RequestCodeType {
    REGISTRATION, AUTHORIZATION
}
