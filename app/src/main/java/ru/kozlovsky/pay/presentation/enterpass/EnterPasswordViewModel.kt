package ru.kozlovsky.pay.presentation.enterpass

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kozlovsky.pay.R
import ru.kozlovsky.pay.core.BaseViewModel
import ru.kozlovsky.pay.core.result.doOnFailure
import ru.kozlovsky.pay.core.result.doOnSuccess
import ru.kozlovsky.pay.domain.model.Credentials
import ru.kozlovsky.pay.domain.model.RequestCode
import ru.kozlovsky.pay.domain.navigation.navigate
import ru.kozlovsky.pay.domain.navigation.popUpToInclusive
import ru.kozlovsky.pay.domain.usecase.AuthorizationUseCase
import ru.kozlovsky.pay.presentation.confirmation.ConfirmationFragment
import ru.kozlovsky.pay.util.extension.getParcelableCompat
import javax.inject.Inject

class EnterPasswordViewModel @Inject constructor(
    private val authorizationUseCase: AuthorizationUseCase,
) : BaseViewModel() {


    private lateinit var requestCode: RequestCode

    override fun reInit(args: Bundle?) {
        super.reInit(args)
        requestCode = args?.getParcelableCompat<RequestCode>(ConfirmationFragment.KEY_REQUEST_CODE)!!
    }

    fun tryToAuthorize(password: String) = viewModelScope.launch {
        authorizationUseCase.invoke(
            Credentials(phone = requestCode.phone, password = password, name = null)
        ).doOnSuccess {
            navigate(
                to = R.id.pinCodeFragment,
                navOptions = popUpToInclusive(R.id.enterPasswordFragment)
            )
        }.doOnFailure {

        }
    }
}