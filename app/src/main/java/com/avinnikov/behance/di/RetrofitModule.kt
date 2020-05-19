package com.avinnikov.behance.di

import com.avinnikov.behance.BuildConfig
import com.avinnikov.behance.extensions.logd
import com.avinnikov.behance.restapi.BehanceService
import com.avinnikov.behance.restapi.ClientIdInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://api.behance.net/v2/"

val retrofitModule = module {
    fun createOkHttpClient(): OkHttpClient {
        val loggingInterceptor =
            HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    logd(message, "OkHttp Behance")
                }
            })

        loggingInterceptor.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(ClientIdInterceptor())
            // TODO: use it if you need to track server errors
            //.addInterceptor(ErrorInterceptor())
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    fun createMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    fun createRetrofitService(moshi: Moshi, client: OkHttpClient): BehanceService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build().create(BehanceService::class.java)

    single { createOkHttpClient() }
    single { createMoshi() }
    single { createRetrofitService(get(), get()) }
}