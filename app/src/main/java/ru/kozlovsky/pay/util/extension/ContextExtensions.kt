package ru.kozlovsky.pay.util.extension

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Context.showKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    view.postDelayed(
        {
            inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        },
        SHOW_KEYBOARD_DELAY
    )
}

private const val SHOW_KEYBOARD_DELAY = 300L