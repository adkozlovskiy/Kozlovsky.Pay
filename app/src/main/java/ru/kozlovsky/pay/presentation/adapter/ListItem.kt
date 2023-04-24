package ru.kozlovsky.pay.presentation.adapter

/**
 * Абстрактный класс для сравнения элементов [CompositeAdapter]
 *
 * Все элементы списка должны наследоваться от этого класса, чтобы [CompositeDiffCallback] корректно работал.
 */
abstract class ListItem {

    /**
     * Сравнение эквивалентности элементов по уникальному идентификатору
     *
     * Рекомендуется всегда переопределять для более точной анимации.
     */
    open fun areItemsTheSame(other: ListItem): Boolean {
        return this == other
    }

    /**
     * Сравнение эквивалентности элементов по всему контенту
     */
    open fun areContentsTheSame(other: ListItem): Boolean {
        return this == other
    }

    fun payload(other: Any): Payloadable = Payloadable.None

    /**
     * Simple marker interface for payloads
     */
    interface Payloadable {
        object None : Payloadable
    }
}