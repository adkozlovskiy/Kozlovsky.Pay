package ru.kozlovsky.pay.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.view.isVisible
import ru.kozlovsky.pay.R
import ru.kozlovsky.pay.databinding.ViewPrimaryButtonBinding

class PrimaryButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding = ViewPrimaryButtonBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    var text: String = ""
        set(value) {
            field = value
            binding.text.text = value
        }

    var loading: Boolean = false
        set(value) {
            field = value
            binding.loading.isVisible = value
            binding.text.isVisible = !value
        }

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.PrimaryButton,
            0,
            0
        ).use {
            it.apply {
                text = getString(R.styleable.PrimaryButton_text).orEmpty()
                loading = getBoolean(R.styleable.PrimaryButton_loading, false)
            }
        }
    }

    override fun setOnClickListener(l: OnClickListener?) {
        super.setOnClickListener(l)
        binding.card.setOnClickListener(l)
    }
}