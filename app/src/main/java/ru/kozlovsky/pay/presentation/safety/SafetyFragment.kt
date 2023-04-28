package ru.kozlovsky.pay.presentation.safety

import dagger.hilt.android.AndroidEntryPoint
import ru.kozlovsky.pay.core.BaseFragment
import ru.kozlovsky.pay.databinding.FragmentSafetyBinding
import ru.kozlovsky.pay.domain.navigation.navigateUp

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
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
    }
}