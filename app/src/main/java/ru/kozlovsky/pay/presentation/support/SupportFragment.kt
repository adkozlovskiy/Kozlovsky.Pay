package ru.kozlovsky.pay.presentation.support

import dagger.hilt.android.AndroidEntryPoint
import ru.kozlovsky.pay.core.BaseFragment
import ru.kozlovsky.pay.databinding.FragmentSupportBinding
import ru.kozlovsky.pay.domain.navigation.navigateUp
import ru.kozlovsky.pay.util.extension.showKeyboard

@AndroidEntryPoint
class SupportFragment : BaseFragment<SupportViewModel, FragmentSupportBinding>() {
    override val viewModelClass: Class<SupportViewModel>
        get() = SupportViewModel::class.java

    override fun getViewBinding(): FragmentSupportBinding {
        return FragmentSupportBinding.inflate(layoutInflater)
    }

    override fun configureView() {
        super.configureView()
        with(binding) {
            ftToolbar.setNavigationOnClickListener {
                viewModel.navigateUp()
            }
            showKeyboard(input.isiInput)
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
    }
}