package ru.kozlovsky.pay.domain.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.NavigationRes
import androidx.navigation.NavOptions
import ru.kozlovsky.pay.core.BaseViewModel

fun BaseViewModel.navigate(@IdRes to: Int, args: Bundle? = null, navOptions: NavOptions? = null) {
    navigationEvent.tryEmit(
        NavigationEvent.Navigation(to, args, navOptions)
    )
}

fun BaseViewModel.navigateUp() {
    navigationEvent.tryEmit(NavigationEvent.Up)
}