package ru.kozlovsky.pay.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.kozlovsky.pay.data.model.dto.AccountStatus
import ru.kozlovsky.pay.presentation.adapter.ListItem
import ru.kozlovsky.pay.util.Constants.ACCOUNT_NUMBER_MASK
import ru.kozlovsky.pay.util.extension.getMaskedText

@Parcelize
data class Account(
    val id: Long,
    val balance: Double,
    val balanceHidden: Boolean = false,
    val status: AccountStatus
) : ListItem(), Parcelable {

    val formattedNumber
        get(): String = id.toString().getMaskedText(ACCOUNT_NUMBER_MASK, 'X', ' ')

    override fun areItemsTheSame(other: ListItem) = id == (other as Account).id
}