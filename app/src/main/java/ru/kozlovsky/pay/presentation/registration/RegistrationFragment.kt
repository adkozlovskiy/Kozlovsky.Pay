package ru.kozlovsky.pay.presentation.registration

import dagger.hilt.android.AndroidEntryPoint
import ru.kozlovsky.pay.core.BaseFragment
import ru.kozlovsky.pay.databinding.FragmentRegistrationBinding

@AndroidEntryPoint
class RegistrationFragment : BaseFragment<RegistrationViewModel, FragmentRegistrationBinding>() {
    override val viewModelClass: Class<RegistrationViewModel>
        get() = RegistrationViewModel::class.java

    override fun getViewBinding(): FragmentRegistrationBinding {
        return FragmentRegistrationBinding.inflate(layoutInflater)
    }

    override fun configureView() {
        super.configureView()
        with(binding) {
            ftBtnContinue.setOnClickListener {
                viewModel.tryRegister(binding.ftEtPass.text.toString(), binding.ftEtFieldName.text.toString())
            }
        }
    }

}