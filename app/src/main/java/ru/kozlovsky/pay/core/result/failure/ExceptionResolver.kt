package ru.kozlovsky.pay.core.result.failure

fun interface ExceptionResolver<out T : Failure> {
    fun resolve(ex: Exception): T
}