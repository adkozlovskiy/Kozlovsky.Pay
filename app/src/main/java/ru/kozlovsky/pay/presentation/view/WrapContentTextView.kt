package ru.kozlovsky.pay.presentation.view

import android.content.Context
import android.text.Layout
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import kotlin.math.ceil

class WrapContentTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (layout == null || layout.lineCount < MULTILINE_COUNT) return

        val width = ceil(
            getMaxLineWidth(layout)
        ).toInt() + compoundPaddingStart + compoundPaddingEnd
        val resolveWidthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY)
        setMeasuredDimension(resolveWidthMeasureSpec, measuredHeightAndState)
    }

    private fun getMaxLineWidth(layout: Layout): Float {
        return (0 until layout.lineCount).maxOfOrNull { layout.getLineWidth(it) } ?: 0F
    }
}

private const val MULTILINE_COUNT = 2