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
import ru.kozlovsky.pay.data.model.dto.AccountStatus
import ru.kozlovsky.pay.domain.model.Account
import ru.kozlovsky.pay.domain.model.RecentRecipient
import ru.kozlovsky.pay.domain.navigation.navigate
import ru.kozlovsky.pay.domain.navigation.navigateUp
import ru.kozlovsky.pay.presentation.adapter.ListItem
import ru.kozlovsky.pay.presentation.detail.AccountDetailsFragment.Companion.KEY_ACCOUNT
import javax.inject.Inject

class HomeViewModel @Inject constructor(

) : BaseViewModel() {

    private var stateBalanceHidden = false

    private val _recentRecipients = MutableStateFlow(emptyList<RecentRecipient>())
    val recentRecipients: StateFlow<List<RecentRecipient>> = _recentRecipients.asStateFlow()

    private val _accounts = MutableStateFlow(emptyList<Account>())
    val accounts: StateFlow<List<Account>> = _accounts.asStateFlow()

    override fun reInit(args: Bundle?) {
        super.reInit(args)
        _accounts.value = listOf(
            Account(id = 3817117937,
                balance = 1221312.1,
                status = AccountStatus.ACTIVE),
            Account(id = 3817117937,
                balance = 1221312.1,
                status = AccountStatus.ACTIVE)
        )
        _recentRecipients.value = listOf(
            RecentRecipient("Теплов А.",
                "https://sun9-17.userapi.com/impg/cC1aFq2Asr9tJwBCTpKa99cwoJVmynRhvobC4Q/9In9fUcPc2U.jpg?size=1071x1080&quality=95&sign=859861d91d20bf5dc17a6ed81c45d280&type=album"),
            RecentRecipient("Касьянов М.",
                "https://sun9-17.userapi.com/impg/cC1aFq2Asr9tJwBCTpKa99cwoJVmynRhvobC4Q/9In9fUcPc2U.jpg?size=1071x1080&quality=95&sign=859861d91d20bf5dc17a6ed81c45d280&type=album"),
            RecentRecipient("Козловский А.",
                "https://sun9-17.userapi.com/impg/cC1aFq2Asr9tJwBCTpKa99cwoJVmynRhvobC4Q/9In9fUcPc2U.jpg?size=1071x1080&quality=95&sign=859861d91d20bf5dc17a6ed81c45d280&type=album"),
            RecentRecipient("Гладкий Н.",
                "https://sun9-17.userapi.com/impg/cC1aFq2Asr9tJwBCTpKa99cwoJVmynRhvobC4Q/9In9fUcPc2U.jpg?size=1071x1080&quality=95&sign=859861d91d20bf5dc17a6ed81c45d280&type=album")
        )
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