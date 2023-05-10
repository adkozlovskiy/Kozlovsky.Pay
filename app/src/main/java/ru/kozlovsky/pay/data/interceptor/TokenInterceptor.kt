package ru.kozlovsky.pay.data.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import ru.kozlovsky.pay.domain.Session
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenInterceptor @Inject constructor(
    private val session: Session,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = session.getAccessToken()
        Log.d("TAG", "intercept: $token")
        val requestBuilder = chain.request().newBuilder()
        if (token != null) {
            requestBuilder.addHeader("Authorization", token)
        }
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}