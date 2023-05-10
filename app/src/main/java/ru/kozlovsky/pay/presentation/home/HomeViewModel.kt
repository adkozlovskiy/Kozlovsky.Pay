package ru.kozlovsky.pay.presentation.home

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.kozlovsky.pay.R
import ru.kozlovsky.pay.core.BaseViewModel
import ru.kozlovsky.pay.core.result.doOnFailure
import ru.kozlovsky.pay.core.result.doOnSuccess
import ru.kozlovsky.pay.data.model.dto.toAccount
import ru.kozlovsky.pay.data.model.dto.toRecentRecipient
import ru.kozlovsky.pay.domain.model.Account
import ru.kozlovsky.pay.domain.model.RecentRecipient
import ru.kozlovsky.pay.domain.navigation.navigate
import ru.kozlovsky.pay.domain.usecase.GetFullCustomerInfoUseCase
import ru.kozlovsky.pay.presentation.adapter.ListItem
import ru.kozlovsky.pay.presentation.detail.AccountDetailsFragment.Companion.KEY_ACCOUNT
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getFullCustomerInfoUseCase: GetFullCustomerInfoUseCase,
) : BaseViewModel() {

    private var stateBalanceHidden = false

    private val _recentRecipients = MutableStateFlow(emptyList<RecentRecipient>())
    val recentRecipients: StateFlow<List<RecentRecipient>> = _recentRecipients.asStateFlow()

    private val _accounts = MutableStateFlow(emptyList<Account>())
    val accounts: StateFlow<List<Account>> = _accounts.asStateFlow()

    override fun reInit(args: Bundle?) {
        super.reInit(args)
        loadFullPersonInfo()
    }

    fun loadFullPersonInfo() = viewModelScope.launch {
        getFullCustomerInfoUseCase.invoke(Unit)
            .doOnFailure {

            }
            .doOnSuccess { response ->
                _accounts.value = response.customer.accounts.orEmpty().map { it.toAccount() }
                _recentRecipients.value = response.recentRecipients.map { it.toRecentRecipient() }
            }
    }

    fun onShake() = viewModelScope.launch {
        stateBalanceHidden = stateBalanceHidden.not()
        _accounts.update {
            it.map { a -> a.copy(balanceHidden = stateBalanceHidden) }
        }
    }

    fun navigateToQrFragment(item: ListItem) {
        (item as? Account)?.let {
            navigate(
                to = R.id.accountDetailsFragment,
                args = bundleOf(KEY_ACCOUNT to it)
            )
        }
    }

    fun navigateToPrimaryAccountQr() {
        val primaryAccount = _accounts.value.firstOrNull()
        primaryAccount?.let {
            navigate(
                to = R.id.qrFragment,
                args = bundleOf(KEY_ACCOUNT to it)
            )
        }
    }
}