package ru.kozlovsky.pay.presentation.confirmation

import androidx.core.widget.doAfterTextChanged
import dagger.hilt.android.AndroidEntryPoint
import ru.kozlovsky.pay.core.BaseFragment
import ru.kozlovsky.pay.databinding.FragmentConfirmationBinding
import ru.kozlovsky.pay.domain.navigation.navigateUp

@AndroidEntryPoint
class ConfirmationFragment : BaseFragment<ConfirmationViewModel, FragmentConfirmationBinding>() {
    override val viewModelClass: Class<ConfirmationViewModel>
        get() = ConfirmationViewModel::class.java

    override fun getViewBinding(): FragmentConfirmationBinding {
        return FragmentConfirmationBinding.inflate(layoutInflater)
    }

    override fun configureView() {
        super.configureView()
        with(binding) {
            fpToolbar.setNavigationOnClickListener {
                viewModel.navigateUp()
            }
            code.doAfterTextChanged {
                viewModel.onCodeChanged(it.toString())
            }
        }
    }

    companion object {
        const val KEY_REQUEST_CODE_RESPONSE = "request.code.response"
        const val KEY_REQUEST_CODE = "request.code"
    }
}