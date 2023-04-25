package ru.kozlovsky.pay.presentation.pager

import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import androidx.annotation.DimenRes
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import ru.kozlovsky.pay.R
import kotlin.math.roundToInt

/**
 * Коллбэк для [ViewPager2], поддерживающий элементы динамической высоты
 * с увеличением и уменьшением высоты при скролле
 *
 * Если вы программно устанавливаете дополнительный вертикальный отступ элементам внутри
 * ViewPager, он должен быть учтен в высоте.
 * @property viewPager2 [ViewPager2]
 * @param additionalVerticalPaddingRes дополнительный вертикальный отступ
 */
class AdjustingPageChangeCallback(
    private val viewPager2: ViewPager2,
    @DimenRes additionalVerticalPaddingRes: Int = R.dimen.padding0,
) : ViewPager2.OnPageChangeCallback() {

    private val paddingVertical = viewPager2.resources.getDimensionPixelOffset(
        additionalVerticalPaddingRes
    )
    private val recyclerView = viewPager2.getChildAt(0) as RecyclerView

    private fun measurePage(view: View) {
        val wMeasureSpec = View.MeasureSpec.makeMeasureSpec(view.width, View.MeasureSpec.EXACTLY)
        val hMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        view.measure(wMeasureSpec, hMeasureSpec)
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        super.onPageScrolled(position, positionOffset, positionOffsetPixels)
        if (positionOffset == 0f) {
            viewPager2.post {
                val currentView: View? = recyclerView.getChildAt(position)
                currentView?.let {
                    measurePage(it)
                    if (viewPager2.layoutParams.height != it.measuredHeight + paddingVertical) {
                        viewPager2.updateLayoutParams<MarginLayoutParams> {
                            height = it.measuredHeight + paddingVertical
                        }
                    }
                }
            }
        } else {
            val currentView: View? = recyclerView.getChildAt(position + 1)
            val guideView: View? = recyclerView.getChildAt(position)

            if (currentView != null && guideView != null) {
                viewPager2.post {
                    measurePage(currentView)
                    measurePage(guideView)

                    val delta = (guideView.measuredHeight - currentView.measuredHeight) * (1 - positionOffset)
                    val recalculatedHeight = currentView.measuredHeight + delta.roundToInt() + paddingVertical

                    if (viewPager2.layoutParams.height != recalculatedHeight) {
                        viewPager2.updateLayoutParams<MarginLayoutParams> {
                            height = recalculatedHeight
                        }
                    }
                }
            }
        }
    }
}