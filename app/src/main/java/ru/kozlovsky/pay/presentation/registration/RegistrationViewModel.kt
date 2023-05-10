package ru.kozlovsky.pay.presentation.registration

import android.os.Bundle
import android.util.Log
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
import ru.kozlovsky.pay.domain.usecase.RegistrationUseCase
import ru.kozlovsky.pay.presentation.confirmation.ConfirmationFragment
import ru.kozlovsky.pay.util.extension.getParcelableCompat
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val registrationUseCase: RegistrationUseCase,
) : BaseViewModel() {

    private lateinit var phone: String

    override fun reInit(args: Bundle?) {
        super.reInit(args)
        phone = args?.getParcelableCompat<RequestCode>(ConfirmationFragment.KEY_REQUEST_CODE)?.phone!!
    }

    fun tryRegister(password: String, name: String?) = viewModelScope.launch {
        registrationUseCase.invoke(
            Credentials(phone, password, name)
        ).doOnSuccess {
            navigate(
                to = R.id.navigation_home, navOptions = popUpToInclusive(R.id.registrationFragment)
            )
        }.doOnFailure {
            Log.d("TAG", "tryRegister: ")
        }
    }
}