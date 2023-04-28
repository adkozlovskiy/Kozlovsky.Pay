package ru.kozlovsky.pay.presentation.recovery

import dagger.hilt.android.AndroidEntryPoint
import ru.kozlovsky.pay.R
import ru.kozlovsky.pay.core.BaseFragment
import ru.kozlovsky.pay.databinding.FragmentRecoveryPasswordBinding
import ru.kozlovsky.pay.domain.navigation.navigate
import ru.kozlovsky.pay.domain.navigation.navigateUp

@AndroidEntryPoint
class RecoveryPasswordFragment : BaseFragment<RecoveryPasswordViewModel, FragmentRecoveryPasswordBinding>() {
    override val viewModelClass: Class<RecoveryPasswordViewModel>
        get() = RecoveryPasswordViewModel::class.java

    override fun getViewBinding(): FragmentRecoveryPasswordBinding {
        return FragmentRecoveryPasswordBinding.inflate(layoutInflater)
    }

    override fun configureView() {
        super.configureView()
        with(binding) {
            ftToolbar.setNavigationOnClickListener {
                viewModel.navigateUp()
            }
            ftBtnContinue.setOnClickListener {
                // todo temp solution
                viewModel.navigate(to = R.id.navigation_home)
            }
        }
    }
}