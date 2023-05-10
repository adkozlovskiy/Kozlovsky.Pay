package ru.kozlovsky.pay.core

import android.os.Bundle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.kozlovsky.pay.domain.navigation.Navigable
import ru.kozlovsky.pay.domain.navigation.NavigationEvent

abstract class BaseViewModel : ViewModel(), Navigable {

    override val navigationEvent = MutableSharedFlow<NavigationEvent>(
        extraBufferCapacity = 1
    )

    protected val _loading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    open fun reInit(args: Bundle?) {}

    open fun onResult(value: Any?) {}
}