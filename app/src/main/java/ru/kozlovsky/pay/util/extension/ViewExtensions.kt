package ru.kozlovsky.pay.util.extension

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import java.lang.ref.WeakReference

fun View.subscribeOnChangeListener(
    adapter: RecyclerView.Adapter<*>,
    onViewAttached: (RecyclerView.Adapter<*>) -> Unit,
    onViewDetached: () -> Unit,
) {
    val weakAdapter = WeakReference(adapter)
    addOnAttachStateChangeListener(
        object : View.OnAttachStateChangeListener {

            override fun onViewAttachedToWindow(v: View) {
                weakAdapter.get()?.let { adapter ->
                    onViewAttached(adapter)
                }
            }

            override fun onViewDetachedFromWindow(v: View) {
                onViewDetached()
            }
        }
    )
}