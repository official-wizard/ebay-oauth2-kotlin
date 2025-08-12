package com.ebay.oauth2.core

import com.ebay.oauth2.data.EBayCredentials
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class CoreClient(credentials: EBayCredentials, debugging: Boolean = false) {

    private val httpClient: OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(AuthenticatorInterceptor(credentials))

        if (debugging) {
            addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
        }
    }.build()

    val retroClient = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(NetworkResponseAdapterFactory())
        .baseUrl(credentials.environment.apiEndpoint)
        .client(httpClient)
        .build()
}