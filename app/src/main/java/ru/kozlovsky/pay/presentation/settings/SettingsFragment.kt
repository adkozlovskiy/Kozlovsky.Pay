package ru.kozlovsky.pay.presentation.settings

import androidx.core.view.isVisible
import dagger.hilt.android.AndroidEntryPoint
import ru.kozlovsky.pay.R
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
            phone.title.text = getString(R.string.settings_phone)
            phone.value.text = "+7 (985) 938-87-71"

            password.title.text = "Сменить пароль"
            password.value.isVisible = false

            username.title.text = "Имя пользователя"
            username.value.text = "adkozlovsky"

            shake.title.text = "Скрыть баланс при перевороте"

            theme.value.text = "Темная"

            byPush.title.text = "push уведомления"
            bySms.title.text = "по СМС"
        }
    }
}