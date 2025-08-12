package com.ebay.oauth2.core

import com.ebay.oauth2.data.EBayCredentials
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*

/**
 * This [Interceptor] takes care of the authentication process between requests
 */
class AuthenticatorInterceptor(private val credentials: EBayCredentials): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        request.addHeader("Authorization", getAuthorization())

        return chain.proceed(request.build())
    }

    private fun getAuthorization(): String {
        return "Basic " + Base64.getEncoder().encodeToString(
            "${credentials.appId}:${credentials.certId}".toByteArray()
        )
    }
}
