package ru.kozlovsky.pay.util.extension

import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import ru.kozlovsky.pay.R
import ru.kozlovsky.pay.presentation.adapter.CompositeAdapter

/**
 * Инициализация viewPager для главной c автоматической отпиской
 *
 * @receiver [ViewPager2]
 * @param adapter
 */
fun ViewPager2.setupPagerAdapter(adapter: CompositeAdapter) {
    val recyclerView = getChildAt(0) as RecyclerView
    recyclerView.apply {
        overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        clipToPadding = false
        clipChildren = false
    }
    setPageTransformer(
        MarginPageTransformer(
            resources.getDimensionPixelSize(R.dimen.padding8)
        )
    )
    setupAdapter(adapter)
}

fun ViewPager2.updateOffscreenPageLimit(items: List<*>) {
    if (items.isNotEmpty()) {
        offscreenPageLimit = items.size
    }
}

fun ViewPager2.setupAdapter(adapter: RecyclerView.Adapter<*>) {
    setAdapter(adapter)

    subscribeOnChangeListener(
        adapter,
        {
            if (getAdapter() == null) {
                setAdapter(it)
            }
        },
        { setAdapter(null) }
    )
}