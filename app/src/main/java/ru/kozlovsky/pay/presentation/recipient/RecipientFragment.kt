package ru.kozlovsky.pay.presentation.recipient

import dagger.hilt.android.AndroidEntryPoint
import ru.kozlovsky.pay.core.BaseFragment
import ru.kozlovsky.pay.databinding.FragmentRecipientBinding
import ru.kozlovsky.pay.domain.navigation.navigateUp

@AndroidEntryPoint
class RecipientFragment : BaseFragment<RecipientViewModel, FragmentRecipientBinding>() {
    override val viewModelClass: Class<RecipientViewModel>
        get() = RecipientViewModel::class.java

    override fun getViewBinding(): FragmentRecipientBinding {
        return FragmentRecipientBinding.inflate(layoutInflater)
    }

    override fun configureView() {
        super.configureView()
        with(binding) {
            frToolbar.setNavigationOnClickListener {
                viewModel.navigateUp()
            }
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
    }

    companion object {
        const val KEY_PHONE_NUMBER = "phone.number"
    }
}