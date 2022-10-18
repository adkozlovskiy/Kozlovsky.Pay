package ru.kozlovsky.pay.presentation.home

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kozlovsky.pay.core.BaseViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(

) : BaseViewModel() {

    override fun reInit(args: Bundle?) {
        super.reInit(args)
    }

    fun onShake() = viewModelScope.launch {

    }
}