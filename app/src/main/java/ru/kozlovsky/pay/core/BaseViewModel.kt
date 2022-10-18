package ru.kozlovsky.pay.core

import android.os.Bundle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import ru.kozlovsky.pay.domain.navigation.Navigable
import ru.kozlovsky.pay.domain.navigation.NavigationEvent

abstract class BaseViewModel : ViewModel(), Navigable {

    override val navigationEvent = MutableSharedFlow<NavigationEvent>(
        extraBufferCapacity = 1
    )

    open fun reInit(args: Bundle?) {}

}