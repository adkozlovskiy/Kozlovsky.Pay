package ru.kozlovsky.pay.presentation.enterpass

import dagger.hilt.android.AndroidEntryPoint
import ru.kozlovsky.pay.core.BaseFragment
import ru.kozlovsky.pay.databinding.FragmentEnterPasswordBinding

@AndroidEntryPoint
class EnterPasswordFragment : BaseFragment<EnterPasswordViewModel, FragmentEnterPasswordBinding>() {
    override val viewModelClass: Class<EnterPasswordViewModel>
        get() = EnterPasswordViewModel::class.java

    override fun getViewBinding(): FragmentEnterPasswordBinding {
        return FragmentEnterPasswordBinding.inflate(layoutInflater)
    }

    override fun configureView() {
        super.configureView()
        with(binding) {
            ftBtnContinue.setOnClickListener {
                viewModel.tryToAuthorize(binding.ftEtPass.text.toString())
            }
        }
    }
}