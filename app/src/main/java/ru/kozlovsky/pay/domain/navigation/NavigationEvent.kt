package ru.kozlovsky.pay.domain.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavOptions

sealed class NavigationEvent {

    object Up : NavigationEvent()

    data class Navigation(
        @IdRes val targetRes: Int,
        val args: Bundle? = null,
        val navOptions: NavOptions? = null,
        @IdRes val popBackStack: Int? = null
    ) : NavigationEvent()

    data class Result(
        val value: Any?
    ) : NavigationEvent()
}
