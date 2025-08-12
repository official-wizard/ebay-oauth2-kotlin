package com.ebay.oauth2.data.pojo

import com.google.gson.annotations.SerializedName

class AccessToken {

    data class Response(

        @SerializedName("access_token")
        val accessToken: String?,

        @SerializedName("expires_in")
        val expiresIn: Long?,

        @SerializedName("refresh_token")
        val refreshToken: String?,

        @SerializedName("refresh_token_expires_in")
        val refreshTokenExpiresIn: Long?,

        @SerializedName("token_type")
        val tokenType: String
    )
}