package ru.kozlovsky.pay.domain.model

import ru.kozlovsky.pay.presentation.adapter.ListItem

data class RecentRecipient(
    val customer: String,
    val customerImage: String,
): ListItem()