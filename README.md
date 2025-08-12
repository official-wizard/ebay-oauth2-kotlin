
<p align="center" dir="auto">
    <a href="https://developer.ebay.com/api-docs/static/authorization_guide_landing.html" rel="nofollow">
        <img src="./logo.png" width="300" alt="eBay Logo" style="max-width: 100%;"/>
    </a>
</p>

<h1 align="center">eBay OAuth2 API</h1>

## Installation

To begin, import the library using jitpack.io.

You can include jitpack in your `pom.xml` by adding the following jitpack repository:

```xml
<repository>
    <id>jitpack.io</id>
    <url>https://www.jitpack.io</url>
</repository>
```

Then add this `ebay-oauth2-kotlin` dependency to your `pom.xml` project!

```xml
<dependency>    
    <groupId>com.github.official-wizard</groupId>    
    <artifactId>ebay-oauth2-kotlin</artifactId>    
    <version>1.0.0</version>
</dependency>
```

## Usage

### Basic Usage

```kotlin

val credentials = EBayCredentials("<app id>", "<cert id>", "<redirect uri>", Environment.SANDBOX) // Environment.PRODUCTION for production

// api container + utilities
val client: EBayOAuthClient = EBayOAuthClient(credentials)

// api interface
val api: EBayOAuthInterface = client.api

```

#### Generate OAuth2 URL

 A call to this function returns a generated OAuth2 Authorization URL. [Source.](https://developer.ebay.com/api-docs/static/oauth-scopes.html)

```kotlin
val credentials = EBayCredentials("<app id>", "<cert id>", "<redirect uri>", Environment.SANDBOX) // Environment.PRODUCTION for production
val client: EBayOAuthClient = EBayOAuthClient(credentials)

// your generate oauth2 authorization url
val url: String = client.generateAuthorizationUrl(listOf("scope1", "scope2"), "<optional state>")
```

#### Code to Access Tokens

The authorization code grant is a request that mints a new User access token.
Use the token to make requests to API methods that match the scopes configured into the access token.
[Source.](https://developer.ebay.com/api-docs/static/oauth-auth-code-grant-request.html)

```kotlin
val credentials = EBayCredentials("<app id>", "<cert id>", "<redirect uri>", Environment.SANDBOX)
val client: EBayOAuthClient = EBayOAuthClient(credentials)
val api: EBayOAuthInterface = client.api

val response = api.exchangeCodeForAccessToken("<redirect uri>", "<code>")
if (response is NetworkResponse.Success) {
    // handle the data
    val result: AccessToken.Response = response.body

} else if (response is NetworkResponse.Error) {
    // if the server returns an error it be found here
    val errorResponse: Error.Response? = response.body

    // if the api (locally) had an internal error, it'll be found here
    val internalError: Throwable? = response.error
}
```

#### Refresh Access Token

For security, a User access token is short-lived. 
However, a refresh token is long-lived and you can use it to renew a User access token after the token expires. 
The benefit is that you don't need to get the account-owner's consent each time you need to renew their User access token. 
[Source.](https://developer.ebay.com/api-docs/static/oauth-refresh-token-request.html)

```kotlin
val credentials = EBayCredentials("<app id>", "<cert id>", "<redirect uri>", Environment.SANDBOX)
val client: EBayOAuthClient = EBayOAuthClient(credentials)
val api: EBayOAuthInterface = client.api

val response = api.refreshAccessToken("<refresh token>", client.buildScope(listOf("scope1", "scope2")))
if (response is NetworkResponse.Success) {
    // handle the data
    val result: AccessToken.Response = response.body

} else if (response is NetworkResponse.Error) {
    // if the server returns an error it be found here
    val errorResponse: Error.Response? = response.body

    // if the api (locally) had an internal error, it'll be found here
    val internalError: Throwable? = response.error
}
```

#### Get Application Access Token

The client credentials grant is a single request that mints a new Application access token. 
Use the token to make requests to API methods that match the scopes configured into the access token. 
[Source](https://developer.ebay.com/api-docs/static/oauth-client-credentials-grant.html)

```kotlin
val credentials = EBayCredentials("<app id>", "<cert id>", "<redirect uri>", Environment.SANDBOX)
val client: EBayOAuthClient = EBayOAuthClient(credentials)
val api: EBayOAuthInterface = client.api

val response = api.getApplicationToken(client.buildScope(listOf("scope1", "scope2")))
if (response is NetworkResponse.Success) {
    // handle the data
    val result: AccessToken.Response = response.body

} else if (response is NetworkResponse.Error) {
    // if the server returns an error it be found here
    val errorResponse: Error.Response? = response.body

    // if the api (locally) had an internal error, it'll be found here
    val internalError: Throwable? = response.error
}
```

## Testing

Apache maven must be correctly installed on the system.

To run tests for this project using Maven, execute the following command in your terminal:

```bash
mvn test
```

To compile the application, just run:

```bash
mvn clean package
```

## License

Apache License 2.0. See [License File](LICENSE).

