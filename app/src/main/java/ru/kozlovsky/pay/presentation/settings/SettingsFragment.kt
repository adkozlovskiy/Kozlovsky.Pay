package ru.kozlovsky.pay.presentation.settings

import dagger.hilt.android.AndroidEntryPoint
import ru.kozlovsky.pay.core.BaseFragment
import ru.kozlovsky.pay.databinding.FragmentSettingsBinding
import ru.kozlovsky.pay.domain.navigation.navigateUp

@AndroidEntryPoint
class SettingsFragment : BaseFragment<SettingsViewModel, FragmentSettingsBinding>() {
    override val viewModelClass: Class<SettingsViewModel>
        get() = SettingsViewModel::class.java

    override fun getViewBinding(): FragmentSettingsBinding {
        return FragmentSettingsBinding.inflate(layoutInflater)
    }

    override fun configureView() {
        super.configureView()
        with(binding) {
            fsToolbar.setNavigationOnClickListener {
                viewModel.navigateUp()
            }
        }
    }
}