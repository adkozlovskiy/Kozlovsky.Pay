package ru.kozlovsky.pay.presentation.transaction.phone

import android.text.method.DigitsKeyListener
import android.view.inputmethod.EditorInfo
import com.redmadrobot.inputmask.MaskedTextChangedListener
import dagger.hilt.android.AndroidEntryPoint
import ru.kozlovsky.pay.R
import ru.kozlovsky.pay.core.BaseFragment
import ru.kozlovsky.pay.databinding.FragmentPhoneTransactionBinding
import ru.kozlovsky.pay.domain.navigation.navigateUp
import ru.kozlovsky.pay.util.Constants.PHONE_MASK
import ru.kozlovsky.pay.util.extension.collectOnLifecycle
import ru.kozlovsky.pay.util.extension.showKeyboard

@AndroidEntryPoint
class PhoneTransactionFragment : BaseFragment<PhoneTransactionViewModel, FragmentPhoneTransactionBinding>() {

    private val textChangedValueListener = object : MaskedTextChangedListener.ValueListener {
        override fun onTextChanged(
            maskFilled: Boolean,
            extractedValue: String,
            formattedValue: String
        ) {
            viewModel.afterInputChanged(maskFilled, formattedValue)
        }
    }

    override val viewModelClass: Class<PhoneTransactionViewModel>
        get() = PhoneTransactionViewModel::class.java

    override fun getViewBinding(): FragmentPhoneTransactionBinding {
        return FragmentPhoneTransactionBinding.inflate(layoutInflater)
    }

    override fun configureView() {
        super.configureView()
        with(binding) {
            ftToolbar.setNavigationOnClickListener {
                viewModel.navigateUp()
            }
            ftBtnContinue.setOnClickListener {
                viewModel.onContinue()
            }
            showKeyboard(ftEtField)
            ftEtField.keyListener = DigitsKeyListener.getInstance(
                getString(R.string.phone_allowed_digits)
            )
            ftEtField.setRawInputType(EditorInfo.TYPE_CLASS_PHONE)
            MaskedTextChangedListener.installOn(
                ftEtField,
                PHONE_MASK,
                textChangedValueListener
            )
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
        collectOnLifecycle(viewModel.buttonEnabled) {
            binding.ftBtnContinue.isEnabled = it
        }
    }
}