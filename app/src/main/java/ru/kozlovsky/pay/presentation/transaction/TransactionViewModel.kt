package ru.kozlovsky.pay.presentation.transaction

import androidx.core.os.bundleOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.kozlovsky.pay.R
import ru.kozlovsky.pay.core.BaseViewModel
import ru.kozlovsky.pay.domain.navigation.navigate
import ru.kozlovsky.pay.presentation.recipient.RecipientFragment
import javax.inject.Inject

class TransactionViewModel @Inject constructor() : BaseViewModel() {

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
//        navigate(
//            R.id.recipientFragment,
//            bundleOf(RecipientFragment.KEY_PHONE_NUMBER to input)
//        )
    }
}