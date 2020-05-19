package com.avinnikov.behance.restapi

import okhttp3.Interceptor
import okhttp3.Response

class ErrorInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        val body = response.body?.string() ?: ""

        if (!response.isSuccessful && response.code >= 500) {
            // TODO: track server error
            // use request and body
        }

        return response
    }
}