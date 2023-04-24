package ru.kozlovsky.pay.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding

abstract class BaseDelegate<I : ListItem, V : ViewBinding>(
    var onItemClickListener: ((item: I, position: Int) -> Unit)? = null,
    var isForViewPager: Boolean = false,
) {

    abstract fun isRelativeItem(item: ListItem): Boolean

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewHolder(inflater: LayoutInflater, parent: ViewGroup): BaseViewHolder<V, I>

}