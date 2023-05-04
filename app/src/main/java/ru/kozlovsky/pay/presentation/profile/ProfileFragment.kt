package ru.kozlovsky.pay.presentation.profile

import dagger.hilt.android.AndroidEntryPoint
import ru.kozlovsky.pay.R
import ru.kozlovsky.pay.core.BaseFragment
import ru.kozlovsky.pay.databinding.FragmentProfileBinding
import ru.kozlovsky.pay.domain.navigation.navigate

@AndroidEntryPoint
class ProfileFragment : BaseFragment<ProfileViewModel, FragmentProfileBinding>() {
    override val viewModelClass: Class<ProfileViewModel>
        get() = ProfileViewModel::class.java

    override fun getViewBinding(): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(layoutInflater)
    }

    override fun configureView() {
        super.configureView()
        with(binding) {
            itemMyData.title.text = getString(R.string.profile_item_my_data)
            itemSafety.title.text = getString(R.string.profile_item_safety)
            itemSettings.title.text = getString(R.string.profile_item_settings)
            itemSettings.root.setOnClickListener {
                viewModel.navigate(R.id.settingsFragment)
            }
            itemMyData.root.setOnClickListener {
                viewModel.navigate(R.id.customerDataFragment)
            }
            itemSupport.root.setOnClickListener {
                viewModel.navigate(R.id.supportFragment)
            }
            itemSafety.root.setOnClickListener {
                viewModel.navigate(R.id.safetyFragment)
            }
            itemSupport.title.text = getString(R.string.profile_item_support)

            logout.setOnClickListener {
                viewModel.logout()
            }
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
    }
}