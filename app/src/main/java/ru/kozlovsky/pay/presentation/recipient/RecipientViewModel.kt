package ru.kozlovsky.pay.presentation.recipient

import android.os.Bundle
import android.util.Log
import ru.kozlovsky.pay.core.BaseViewModel
import javax.inject.Inject

class RecipientViewModel @Inject constructor() : BaseViewModel() {
    override fun reInit(args: Bundle?) {
        super.reInit(args)
        Log.d("TAG", "reInit: ${args?.getString(RecipientFragment.KEY_PHONE_NUMBER)}")
    }
}