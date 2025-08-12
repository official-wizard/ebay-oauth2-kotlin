package com.ebay.oauth2.data

enum class Environment(val identifier: String, val webEndpoint: String, val apiEndpoint: String) {
    PRODUCTION("api.ebay.com", "https://auth.ebay.com/oauth2/authorize", "https://api.ebay.com/identity/v1/oauth2/token/"),
    SANDBOX("api.sandbox.ebay.com", "https://auth.sandbox.ebay.com/oauth2/authorize", "https://api.sandbox.ebay.com/identity/v1/oauth2/token/");
}