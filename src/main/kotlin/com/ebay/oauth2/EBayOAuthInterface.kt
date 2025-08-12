package com.ebay.oauth2

import co.infinum.retromock.meta.Mock
import co.infinum.retromock.meta.MockResponse
import com.ebay.oauth2.data.pojo.AccessToken
import com.ebay.oauth2.data.pojo.Error
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.POST
import retrofit2.http.Query

interface EBayOAuthInterface {

    @Mock @MockResponse(body = "/identity/v1/oauth2/token/authorization_code.json")
    @POST("/identity/v1/oauth2/token")
    suspend fun exchangeCodeForAccessToken(
        @Query("redirect_uri") redirectUri: String,
        @Query("code") code: String,
        @Query("grant_type") grantType: String = "authorization_code"
    ): NetworkResponse<AccessToken.Response, Error.Response>

    @Mock @MockResponse(body = "/identity/v1/oauth2/token/refresh_token.json")
    @POST("/identity/v1/oauth2/token")
    suspend fun refreshAccessToken(
        @Query("refresh_token") refreshToken: String,
        @Query("scope") scopes: String,
        @Query("grant_type") grantType: String = "refresh_token"
    ): NetworkResponse<AccessToken.Response, Error.Response>

    @Mock @MockResponse(body = "/identity/v1/oauth2/token/client_credentials.json")
    @POST("/identity/v1/oauth2/token")
    suspend fun getApplicationToken(
        @Query("scope") scopes: String,
        @Query("grant_type") grantType: String = "client_credentials"
    ): NetworkResponse<AccessToken.Response, Error.Response>
}