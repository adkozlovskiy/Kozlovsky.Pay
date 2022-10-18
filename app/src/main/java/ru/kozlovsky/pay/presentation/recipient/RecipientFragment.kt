package ru.kozlovsky.pay.presentation.recipient

import dagger.hilt.android.AndroidEntryPoint
import ru.kozlovsky.pay.core.BaseFragment
import ru.kozlovsky.pay.databinding.FragmentRecipientBinding

@AndroidEntryPoint
class RecipientFragment : BaseFragment<RecipientViewModel, FragmentRecipientBinding>() {
    override val viewModelClass: Class<RecipientViewModel>
        get() = RecipientViewModel::class.java

    override fun getViewBinding(): FragmentRecipientBinding {
        return FragmentRecipientBinding.inflate(layoutInflater)
    }

    override fun observeViewModel() {
        super.observeViewModel()
    }
}