package ru.kozlovsky.pay.presentation.adapter

import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(
    @DimenRes private val spaceRes: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val dimensionInDp = view.resources.getDimensionPixelSize(spaceRes)
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = dimensionInDp
            }
            left = dimensionInDp
            right = dimensionInDp
            bottom = dimensionInDp
        }
    }
}