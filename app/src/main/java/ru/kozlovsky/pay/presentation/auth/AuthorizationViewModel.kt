package ru.kozlovsky.pay.presentation.auth

import androidx.core.os.bundleOf
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kozlovsky.pay.R
import ru.kozlovsky.pay.core.BaseViewModel
import ru.kozlovsky.pay.core.result.doOnFailure
import ru.kozlovsky.pay.core.result.doOnSuccess
import ru.kozlovsky.pay.domain.model.RequestCode
import ru.kozlovsky.pay.domain.navigation.navigate
import ru.kozlovsky.pay.domain.usecase.RequestCodeUseCase
import ru.kozlovsky.pay.presentation.confirmation.ConfirmationFragment
import javax.inject.Inject

class AuthorizationViewModel @Inject constructor(
    private val requestCodeUseCase: RequestCodeUseCase,
) : BaseViewModel() {

    fun requestCode(phone: String) = viewModelScope.launch {
        _loading.value = true
        requestCodeUseCase.invoke(
            RequestCode(phone)
        ).doOnSuccess {
            val arguments = bundleOf(
                ConfirmationFragment.KEY_REQUEST_CODE_RESPONSE to it,
                ConfirmationFragment.KEY_REQUEST_CODE to RequestCode(phone)
            )
            navigate(to = R.id.confirmationFragment, arguments)
        }.doOnFailure {

        }
        _loading.value = false
    }
}