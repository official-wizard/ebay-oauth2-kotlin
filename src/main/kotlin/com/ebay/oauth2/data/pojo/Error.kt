package com.ebay.oauth2.data.pojo

import com.google.gson.annotations.SerializedName

class Error {

    data class Response(

        @SerializedName("error")
        val error: String,

        @SerializedName("error_description")
        val errorDescription: String
    )
}