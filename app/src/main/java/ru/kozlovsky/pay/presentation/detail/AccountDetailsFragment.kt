package ru.kozlovsky.pay.presentation.detail

import androidx.core.os.bundleOf
import dagger.hilt.android.AndroidEntryPoint
import ru.kozlovsky.pay.R
import ru.kozlovsky.pay.core.BaseFragment
import ru.kozlovsky.pay.databinding.FragmentAccountDetailsBinding
import ru.kozlovsky.pay.domain.navigation.navigate
import ru.kozlovsky.pay.domain.navigation.navigateUp
import ru.kozlovsky.pay.util.Constants
import ru.kozlovsky.pay.util.extension.collectOnLifecycle
import ru.kozlovsky.pay.util.extension.formatAsCurrency
import ru.kozlovsky.pay.util.extension.orEmpty
import ru.kozlovsky.pay.util.extension.shareTextPlain

@AndroidEntryPoint
class AccountDetailsFragment : BaseFragment<AccountDetailsViewModel, FragmentAccountDetailsBinding>() {
    override val viewModelClass: Class<AccountDetailsViewModel>
        get() = AccountDetailsViewModel::class.java

    override fun getViewBinding(): FragmentAccountDetailsBinding {
        return FragmentAccountDetailsBinding.inflate(layoutInflater)
    }

    override fun configureView() {
        super.configureView()
        with(binding) {
            ftToolbar.overflowIcon?.setTint(
                resources.getColor(R.color.white_soft, requireContext().theme)
            )
            ftToolbar.setNavigationOnClickListener {
                viewModel.navigateUp()
            }

            fadMcvCredentials.icTitle.text = getString(R.string.credentials)

            fadMcvQrCode.icTitle.text = getString(R.string.qr_code)
            fadMcvQrCode.icSubtitle.text = getString(R.string.qr_code_description)
            fadMcvQrCode.icIcon.setImageResource(R.drawable.ic_qr_code)
            fadMcvQrCode.icIcon.isClickable = false

            fadMcvLink.icTitle.text = getString(R.string.link)
            fadMcvLink.icIcon.setImageResource(R.drawable.ic_link)
            fadMcvLink.icIcon.isClickable = false

            fadMcvQrCode.root.setOnClickListener {
                viewModel.navigate(
                    to = R.id.qrFragment,
                    args = bundleOf(KEY_ACCOUNT to viewModel.account.value)
                )
            }

            fadMcvCredentials.icIcon.setOnClickListener {
                shareTextPlain(
                    viewModel.account.value?.formattedNumber.orEmpty()
                )
            }

            fadMcvCredentials.root.setOnClickListener {
                viewModel.copyAccountNumber()
            }

            fadMcvLink.root.setOnClickListener {
                viewModel.copyAccountLink()
            }
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
        with(binding) {
            collectOnLifecycle(viewModel.account) {
                fadAccountName.text = "Основной счет"
                fadMcvCredentials.icSubtitle.text = getString(
                    R.string.account_number_placeholder, it?.formattedNumber.orEmpty()
                )
                fadMcvLink.icSubtitle.text = "https://sky.pay/go/${it?.formattedNumber.orEmpty()}"
                fadAccountBalance.text = it?.balance?.formatAsCurrency()
            }
        }
    }

    companion object {
        const val KEY_ACCOUNT = "key_account"
    }
}