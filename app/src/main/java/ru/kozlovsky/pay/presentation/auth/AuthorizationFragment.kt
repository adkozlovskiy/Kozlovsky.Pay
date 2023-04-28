package ru.kozlovsky.pay.presentation.auth

import android.text.method.DigitsKeyListener
import android.view.inputmethod.EditorInfo
import com.redmadrobot.inputmask.MaskedTextChangedListener
import dagger.hilt.android.AndroidEntryPoint
import ru.kozlovsky.pay.R
import ru.kozlovsky.pay.core.BaseFragment
import ru.kozlovsky.pay.databinding.FragmentAuthorizationBinding
import ru.kozlovsky.pay.domain.navigation.navigate
import ru.kozlovsky.pay.util.Constants
import ru.kozlovsky.pay.util.extension.showKeyboard

@AndroidEntryPoint
class AuthorizationFragment : BaseFragment<AuthorizationViewModel, FragmentAuthorizationBinding>() {
    override val viewModelClass: Class<AuthorizationViewModel>
        get() = AuthorizationViewModel::class.java

    override fun getViewBinding(): FragmentAuthorizationBinding {
        return FragmentAuthorizationBinding.inflate(layoutInflater)
    }

    private val textChangedValueListener = object : MaskedTextChangedListener.ValueListener {
        override fun onTextChanged(
            maskFilled: Boolean,
            extractedValue: String,
            formattedValue: String
        ) {
            viewModel.afterInputChanged(maskFilled, formattedValue)
        }
    }

    override fun configureView() {
        super.configureView()
        with(binding) {
            showKeyboard(frEtNumber)
            frEtNumber.keyListener = DigitsKeyListener.getInstance(
                getString(R.string.phone_allowed_digits)
            )
            frEtNumber.setRawInputType(EditorInfo.TYPE_CLASS_PHONE)
            MaskedTextChangedListener.installOn(
                frEtNumber,
                Constants.PHONE_MASK,
                textChangedValueListener
            )
            ftBtnContinue.setOnClickListener {
                viewModel.navigate(to = R.id.confirmationFragment)
            }
            forgotPassword.setOnClickListener {
                viewModel.navigate(to = R.id.recoveryPasswordFragment)
            }
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
    }
}