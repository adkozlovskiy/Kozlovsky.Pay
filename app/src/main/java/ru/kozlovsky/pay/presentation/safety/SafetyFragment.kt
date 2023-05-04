package ru.kozlovsky.pay.presentation.safety

import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.core.content.res.ResourcesCompat
import dagger.hilt.android.AndroidEntryPoint
import ru.kozlovsky.pay.R
import ru.kozlovsky.pay.core.BaseFragment
import ru.kozlovsky.pay.databinding.FragmentSafetyBinding
import ru.kozlovsky.pay.domain.navigation.navigateUp
import ru.kozlovsky.pay.util.PrimaryClickableSpan

@AndroidEntryPoint
class SafetyFragment : BaseFragment<SafetyViewModel, FragmentSafetyBinding>() {
    override val viewModelClass: Class<SafetyViewModel>
        get() = SafetyViewModel::class.java

    override fun getViewBinding(): FragmentSafetyBinding {
        return FragmentSafetyBinding.inflate(layoutInflater)
    }

    override fun configureView() {
        super.configureView()
        with(binding) {
            ftToolbar.setNavigationOnClickListener {
                viewModel.navigateUp()
            }
            val deleteAccount = getString(R.string.acc_delete)
            val spannable = SpannableString(accDelete.text)
            val indexOf = spannable.indexOf(deleteAccount)
            spannable.setSpan(
                PrimaryClickableSpan(
                    onClick = {
                        viewModel.deleteAccount()
                    }
                ),
                indexOf,
                indexOf + deleteAccount.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannable.setSpan(
                ForegroundColorSpan(
                    ResourcesCompat.getColor(resources, R.color.blue_accent, requireActivity().theme)
                ),
                indexOf,
                indexOf + deleteAccount.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            accDelete.text = spannable
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
    }
}