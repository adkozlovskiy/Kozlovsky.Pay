package ru.kozlovsky.pay.presentation.pin

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.kozlovsky.pay.R
import ru.kozlovsky.pay.core.BaseViewModel
import ru.kozlovsky.pay.domain.navigation.navigate
import ru.kozlovsky.pay.domain.navigation.popUpToInclusive
import javax.inject.Inject

class PinCodeViewModel @Inject constructor() : BaseViewModel() {

    private val _pinCode = MutableStateFlow("")
    val pinCode: Flow<String> = _pinCode.asStateFlow()

    fun addDigit(digit: String) {
        _pinCode.update { it + digit }
        if (_pinCode.value.length >= 4) {
            navigate(
                to = R.id.navigation_home,
                navOptions = popUpToInclusive(R.id.pinCodeFragment)
            )
        }
    }

    fun actionButtonClicked() {
        if (_pinCode.value.isNotEmpty()) {
            _pinCode.update { it.dropLast(1) }
        } else {
            // start fingerprint
        }
    }
}