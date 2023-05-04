package ru.kozlovsky.pay.util.extension

object StringConstants {
    const val EMPTY_STRING = ""
}

fun String.getMaskedText(mask: String, variableChar: Char, constChar: Char): String {
    val out = StringBuilder()
    var i = 0
    var j = 0
    while (i < mask.length && j < length) {
        if (mask[i] == variableChar) {
            out.append(this[j])
            j++
        } else {
            out.append(constChar)
        }
        i++
    }
    return out.toString()
}

fun String?.orEmpty(): String {
    return this?.let { this } ?: ""
}