package ru.kozlovsky.pay.presentation.profile

import ru.kozlovsky.pay.R
import ru.kozlovsky.pay.core.BaseViewModel
import ru.kozlovsky.pay.domain.Session
import ru.kozlovsky.pay.domain.navigation.navigate
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val session: Session
) : BaseViewModel() {

    fun logout() {
        session.logout()
        navigate(
            to = R.id.authorizationFragment,
            popBackStack = R.id.navigation_home
        )
    }
}