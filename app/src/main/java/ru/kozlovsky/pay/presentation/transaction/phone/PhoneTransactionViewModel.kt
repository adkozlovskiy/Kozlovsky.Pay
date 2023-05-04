package ru.kozlovsky.pay.presentation.transaction.phone

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.kozlovsky.pay.R
import ru.kozlovsky.pay.core.BaseViewModel
import ru.kozlovsky.pay.domain.navigation.navigate
import javax.inject.Inject

class PhoneTransactionViewModel @Inject constructor() : BaseViewModel() {

    private val _buttonEnabled = MutableStateFlow(false)
    val buttonEnabled = _buttonEnabled.asStateFlow()

    private var input: String? = null

    fun afterInputChanged(maskFilled: Boolean, formattedValue: String) {
        if (maskFilled) {
            input = formattedValue
        }
        _buttonEnabled.value = maskFilled
    }

    fun onContinue() {
        if (input == null) return
        navigate(R.id.recipientFragment)
    }
}