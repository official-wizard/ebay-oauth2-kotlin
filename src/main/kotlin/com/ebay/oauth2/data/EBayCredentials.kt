package com.ebay.oauth2.data

data class EBayCredentials(
    val appId: String,
    val certId: String,
    val redirectUri: String,
    val environment: Environment
)