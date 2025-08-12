package com.ebay.oauth2

import com.ebay.oauth2.core.CoreClient
import com.ebay.oauth2.data.EBayCredentials
import java.net.URLEncoder

class EBayOAuthClient(private val credentials: EBayCredentials, debugging: Boolean = false): CoreClient(credentials, debugging) {

    val api: EBayOAuthInterface = retroClient.create(EBayOAuthInterface::class.java)

    fun buildScope(scope: List<String>): String {
        return URLEncoder.encode(scope.joinToString("+"), "UTF-8")
    }

    fun generateIdTokenUrl(nonce: String, state: String? = null): String {

        return buildURL(credentials.environment.webEndpoint, mapOf(
            "client_id" to credentials.appId,
            "response_type" to "id_token",
            "redirect_uri" to URLEncoder.encode(credentials.redirectUri, "UTF-8"),
            "nonce" to nonce,
            "state" to state
        ))
    }

    fun generateAuthorizationUrl(scopes: List<String>, state: String? = null): String {

        return buildURL(credentials.environment.webEndpoint, mapOf(
            "client_id" to credentials.appId,
            "response_type" to "code",
            "redirect_uri" to URLEncoder.encode(credentials.redirectUri, "UTF-8"),
            "scope" to buildScope(scopes),
            "state" to state,
            "auth_type" to "oauth"
        ))
    }

    private fun buildURL(url: String, queries: Map<String, String?> = emptyMap()): String {
        return StringBuilder(url).apply {

            // if query parameter are available
            if (queries.isNotEmpty()) {

                // initialize query
                append("?")

                // loop through queries
                val iterator = queries.entries.iterator()
                while (iterator.hasNext()) {

                    // obtain the next query, ignore if value is null
                    val next = iterator.next()
                    if (next.value != null) {

                        // append query name and value
                        append(next.key + "=" + next.value)

                        // if there's more after this, append '&'
                        if (iterator.hasNext()) {
                            append("&")
                        }
                    }
                }
            }
        }.toString()
    }
}