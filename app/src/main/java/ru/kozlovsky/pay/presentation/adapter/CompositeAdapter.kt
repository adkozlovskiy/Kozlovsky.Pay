package ru.kozlovsky.pay.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding

class CompositeAdapter(
    private val delegates: List<BaseDelegate<*, *>>,
    diffCallback: DiffUtil.ItemCallback<ListItem> = CompositeDiffCallback(),
) : ListAdapter<ListItem, BaseViewHolder<ViewBinding, ListItem>>(diffCallback) {

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ViewBinding, ListItem> {
        val inflater = LayoutInflater.from(parent.context)
        return delegates.find { it.getLayoutId() == viewType }
            ?.getViewHolder(inflater, parent)
            ?.let { it as BaseViewHolder<ViewBinding, ListItem> }
            ?: throw IllegalArgumentException()
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ViewBinding, ListItem>, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return delegates.find { it.isRelativeItem(item) }
            ?.getLayoutId()
            ?: throw IllegalArgumentException()
    }

    class Builder {
        private var diffCallback: DiffUtil.ItemCallback<ListItem> = CompositeDiffCallback()
        private val delegates: MutableList<BaseDelegate<*, *>> = mutableListOf()
        private var commonClickListener: ((ListItem, Int) -> Unit)? = null
        private var isForViewPager: Boolean = false

        fun setForViewPager() {
            isForViewPager = true
        }

        fun setCommonClickListener(listener: (item: ListItem, position: Int) -> Unit): Builder {
            commonClickListener = listener
            return this
        }

        fun setDiffCallback(callback: DiffUtil.ItemCallback<ListItem>): Builder {
            diffCallback = callback
            return this
        }

        fun add(
            delegate: BaseDelegate<out ListItem, *>,
            onClickListener: ((item: ListItem, position: Int) -> Unit)? = null
        ): Builder {
            delegates.add(
                delegate.apply {
                    isForViewPager = this@Builder.isForViewPager
                    onItemClickListener = onClickListener
                    if (onClickListener == null) {
                        onItemClickListener = commonClickListener
                    }
                }
            )
            return this
        }

        fun build(): CompositeAdapter {
            require(delegates.isNotEmpty()) { "Register at least one adapter" }
            return CompositeAdapter(delegates, diffCallback)
        }
    }
}

inline fun compositeAdapter(builder: CompositeAdapter.Builder.() -> CompositeAdapter.Builder): CompositeAdapter {
    return builder.invoke(
        CompositeAdapter.Builder()
    )
        .build()
}