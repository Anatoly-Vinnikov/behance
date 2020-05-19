package com.avinnikov.behance.restapi

import okhttp3.Interceptor
import okhttp3.Response

class ClientIdInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()

        val newUrl = original.url.newBuilder()
            .addQueryParameter("client_id", CLIENT_ID).build()

        original = original.newBuilder().url(newUrl).build()

        return chain.proceed(original)
    }

    companion object {
        private const val CLIENT_ID = "xMrW480v8SrR9J02koQXiIEEMr3uzIfd"
    }
}