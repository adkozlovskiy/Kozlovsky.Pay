package ru.kozlovsky.pay.util.extension

import java.text.NumberFormat
import java.util.Currency

fun Double.formatAsCurrency(): String {
    val format: NumberFormat = NumberFormat.getCurrencyInstance().apply {
        maximumFractionDigits = CURRENCY_DIGITS_AFTER_DOT
        currency = Currency.getInstance(CURRENCY_CODE_RUB)
    }
    return format.format(this)
}

private const val CURRENCY_CODE_RUB = "RUB"
private const val CURRENCY_DIGITS_AFTER_DOT = 2