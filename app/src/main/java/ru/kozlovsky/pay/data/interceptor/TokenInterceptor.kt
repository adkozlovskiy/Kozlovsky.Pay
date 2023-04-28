package ru.kozlovsky.pay.data.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class TokenInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
            .newBuilder()
            .addHeader("Authorization", TOKEN)
            .build()
        return chain.proceed(request)
    }

    companion object {
        const val TOKEN = ""
    }
}