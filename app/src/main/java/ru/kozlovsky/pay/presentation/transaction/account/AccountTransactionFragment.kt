package ru.kozlovsky.pay.presentation.transaction.account

import android.text.method.DigitsKeyListener
import android.view.inputmethod.EditorInfo
import com.redmadrobot.inputmask.MaskedTextChangedListener
import dagger.hilt.android.AndroidEntryPoint
import ru.kozlovsky.pay.R
import ru.kozlovsky.pay.core.BaseFragment
import ru.kozlovsky.pay.databinding.FragmentAccountTransactionBinding
import ru.kozlovsky.pay.domain.navigation.navigateUp
import ru.kozlovsky.pay.presentation.transaction.phone.PhoneTransactionViewModel
import ru.kozlovsky.pay.util.Constants.ACCOUNT_MASK
import ru.kozlovsky.pay.util.extension.collectOnLifecycle
import ru.kozlovsky.pay.util.extension.showKeyboard

@AndroidEntryPoint
class AccountTransactionFragment : BaseFragment<PhoneTransactionViewModel, FragmentAccountTransactionBinding>() {

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

    override fun getViewBinding(): FragmentAccountTransactionBinding {
        return FragmentAccountTransactionBinding.inflate(layoutInflater)
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
            ftEtField.setRawInputType(EditorInfo.TYPE_CLASS_NUMBER)
            MaskedTextChangedListener.installOn(
                ftEtField,
                ACCOUNT_MASK,
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