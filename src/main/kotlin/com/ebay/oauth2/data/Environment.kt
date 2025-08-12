package com.ebay.oauth2.data

enum class Environment(val webEndpoint: String, val apiEndpoint: String) {
    PRODUCTION("https://auth.ebay.com/oauth2/authorize", "https://api.ebay.com/identity/v1/oauth2/token/"),
    SANDBOX("https://auth.sandbox.ebay.com/oauth2/authorize", "https://api.sandbox.ebay.com/identity/v1/oauth2/token/");
}