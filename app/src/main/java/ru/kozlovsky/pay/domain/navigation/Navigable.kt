package ru.kozlovsky.pay.domain.navigation

import kotlinx.coroutines.flow.MutableSharedFlow

interface Navigable {
    val navigationEvent: MutableSharedFlow<NavigationEvent>
}