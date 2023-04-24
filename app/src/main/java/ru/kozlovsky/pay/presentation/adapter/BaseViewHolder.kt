package ru.kozlovsky.pay.presentation.adapter

import android.content.Context
import android.content.res.Resources
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * Базовый класс ViewHolder
 * @property itemViewBinding байндинг
 * @property context [Context]
 * @property resources ресурсы приложения
 * */
abstract class BaseViewHolder<out V : ViewBinding, in I : ListItem>(
    val itemViewBinding: V
) : RecyclerView.ViewHolder(itemViewBinding.root) {

    /**
     * Наполнение viewHolder
     * */
    abstract fun onBind(item: I)

    val context: Context
        get() = itemViewBinding.root.context

    val resources: Resources
        get() = itemViewBinding.root.resources

    /**
     * Получить строку из ресурса
     *
     * @param stringRes Ресурс строки
     */
    protected fun getString(
        @StringRes stringRes: Int,
        vararg args: Any
    ): String = resources.getString(stringRes, *args)
}
