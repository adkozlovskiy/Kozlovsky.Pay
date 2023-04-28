package ru.kozlovsky.pay.presentation.profile

import android.text.SpannableString
import android.text.Spanned
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.textclassifier.TextLinks.TextLinkSpan
import androidx.core.content.res.ResourcesCompat
import dagger.hilt.android.AndroidEntryPoint
import ru.kozlovsky.pay.R
import ru.kozlovsky.pay.core.BaseFragment
import ru.kozlovsky.pay.databinding.FragmentProfileBinding
import ru.kozlovsky.pay.domain.navigation.navigate
import ru.kozlovsky.pay.util.PrimaryClickableSpan

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

            val deleteAccount = getString(R.string.acc_delete)
            val spannable = SpannableString(accDelete.text)
            val indexOf = spannable.indexOf(deleteAccount)
            spannable.setSpan(
                PrimaryClickableSpan(
                    onClick = {
                        viewModel.deleteAccount()
                    }
                ),
                indexOf,
                indexOf + deleteAccount.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannable.setSpan(
                ForegroundColorSpan(
                    ResourcesCompat.getColor(resources, R.color.blue_accent, requireActivity().theme)
                ),
                indexOf,
                indexOf + deleteAccount.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            accDelete.text = spannable
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
    }
}