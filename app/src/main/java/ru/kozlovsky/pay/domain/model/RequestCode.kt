package ru.kozlovsky.pay.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RequestCode(
    val phone: String,
) : Parcelable
