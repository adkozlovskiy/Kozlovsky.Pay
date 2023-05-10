package ru.kozlovsky.pay.domain

import android.content.Context
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Session @Inject constructor(
    @ApplicationContext
    private val appContext: Context,
) {

    private val preferences by lazy {
        appContext.getSharedPreferences(SESSION_PREFERENCES_KEY, Context.MODE_PRIVATE)
    }

    fun authorized() = preferences.getString(ACCESS_TOKEN_KEY, null) != null

    fun logout() = preferences.edit {
        remove(ACCESS_TOKEN_KEY)
    }

    fun storeAccessToken(token: String) = preferences.edit {
        putString(ACCESS_TOKEN_KEY, token)
    }

    fun getAccessToken() = preferences.getString(ACCESS_TOKEN_KEY, null)

    fun storeCode(pin: String) = preferences.edit {
        putString(PIN_KEY, pin)
    }

    fun isCodeCorrect(pin: String) = preferences.getString(PIN_KEY, null) == pin

    companion object {
        private const val SESSION_PREFERENCES_KEY = "session.preferences"
        private const val ACCESS_TOKEN_KEY = "access.token"
        private const val PIN_KEY = "pin"
    }
}
