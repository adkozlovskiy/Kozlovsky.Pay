package ru.kozlovsky.pay.presentation.detail

import android.os.Bundle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.kozlovsky.pay.core.BaseViewModel
import ru.kozlovsky.pay.domain.model.Account
import ru.kozlovsky.pay.presentation.detail.AccountDetailsFragment.Companion.KEY_ACCOUNT
import ru.kozlovsky.pay.util.Clipboard
import ru.kozlovsky.pay.util.extension.getParcelableCompat
import javax.inject.Inject

class AccountDetailsViewModel @Inject constructor(
    private val clipboard: Clipboard,
) : BaseViewModel() {

    private val _account = MutableStateFlow<Account?>(null)
    val account: StateFlow<Account?> = _account.asStateFlow()

    override fun reInit(args: Bundle?) {
        super.reInit(args)
        _account.value = args?.getParcelableCompat(KEY_ACCOUNT)
    }

    fun copyAccountNumber() {
        _account.value?.formattedNumber?.let {
            clipboard.copy("Номер счета", it)
        }
    }

    fun copyAccountLink() {
        _account.value?.formattedNumber?.let {
            clipboard.copy("Ссылка", "https://sky.pay/go/$it")
        }
    }
}