package ru.kozlovsky.pay.presentation.confirmation

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kozlovsky.pay.R
import ru.kozlovsky.pay.core.BaseViewModel
import ru.kozlovsky.pay.core.result.doOnFailure
import ru.kozlovsky.pay.core.result.doOnSuccess
import ru.kozlovsky.pay.domain.model.CheckCodeDto
import ru.kozlovsky.pay.domain.model.RequestCode
import ru.kozlovsky.pay.domain.model.RequestCodeResponse
import ru.kozlovsky.pay.domain.model.RequestCodeType
import ru.kozlovsky.pay.domain.navigation.navigate
import ru.kozlovsky.pay.domain.usecase.CheckCodeUseCase
import ru.kozlovsky.pay.util.extension.getParcelableCompat
import javax.inject.Inject

class ConfirmationViewModel @Inject constructor(
    private val checkCodeUseCase: CheckCodeUseCase,
) : BaseViewModel() {

    private lateinit var requestCodeResponse: RequestCodeResponse

    private lateinit var requestCode: RequestCode

    override fun reInit(args: Bundle?) {
        super.reInit(args)
        requestCodeResponse = args?.getParcelableCompat<RequestCodeResponse>(
            ConfirmationFragment.KEY_REQUEST_CODE_RESPONSE
        )!!
        requestCode = args.getParcelableCompat<RequestCode>(
            ConfirmationFragment.KEY_REQUEST_CODE
        )!!
    }

    private fun checkCode(code: String) = viewModelScope.launch {
        checkCodeUseCase.invoke(
            CheckCodeDto(
                key = requestCodeResponse.key,
                pin = code,
            )
        ).doOnSuccess {
            if (requestCodeResponse.type == RequestCodeType.REGISTRATION) {
                navigate(
                    to = R.id.registrationFragment,
                    args = bundleOf(
                        ConfirmationFragment.KEY_REQUEST_CODE to requestCode
                    )
                )
            } else {
                navigate(
                    to = R.id.enterPasswordFragment,
                    args = bundleOf(
                        ConfirmationFragment.KEY_REQUEST_CODE to requestCode
                    )
                )
            }
        }.doOnFailure {

        }
    }

    fun onCodeChanged(code: String) = viewModelScope.launch {
        if (code.isDigitsOnly() && code.length == 4) {
            checkCode(code)
        }
    }
}