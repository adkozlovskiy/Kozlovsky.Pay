package ru.kozlovsky.pay.util

import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View

class PrimaryClickableSpan(
    private val onClick: (View) -> Unit,
) : ClickableSpan() {
    override fun onClick(widget: View) {
        onClick.invoke(widget)
    }

    override fun updateDrawState(ds: TextPaint) {
        super.updateDrawState(ds)
        ds.isUnderlineText = false
    }
}