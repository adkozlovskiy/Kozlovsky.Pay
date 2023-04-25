package ru.kozlovsky.pay.util.extension

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


inline fun <T> Fragment.collectOnLifecycle(
    flow: Flow<T>,
    lifecycleOwner: LifecycleOwner = viewLifecycleOwner,
    state: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline block: (T) -> Unit
) = lifecycleOwner.lifecycleScope.launch {
    lifecycleOwner.repeatOnLifecycle(state) {
        flow.collect {
            block(it)
        }
    }
}

fun Fragment.showKeyboard(et: EditText) {
    et.requestFocus()
    requireContext().showKeyboard(
        view = activity?.currentFocus ?: View(requireContext())
    )
}

fun Fragment.openSettings() {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    val uri = Uri.fromParts("package", requireActivity().packageName, null)
    intent.data = uri
    startActivity(intent)
}

fun Fragment.shareTextPlain(text: String) {
    val sendIntent = Intent()
    sendIntent.action = Intent.ACTION_SEND
    sendIntent.putExtra(Intent.EXTRA_TEXT, text)
    sendIntent.type = "text/plain"

    val shareIntent = Intent.createChooser(sendIntent, null)
    startActivity(shareIntent)
}