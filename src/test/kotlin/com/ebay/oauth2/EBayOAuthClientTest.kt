package com.ebay.oauth2

import co.infinum.retromock.Retromock
import com.ebay.oauth2.data.EBayCredentials
import com.ebay.oauth2.data.Environment
import com.ebay.oauth2.test.ResourceBodyFactory
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

class EBayOAuthClientTest {

    @Test
    fun `check exchangeCodeForAccessToken`() {

        runBlocking {
            val ebay = createMockedInterface()

            val response = ebay.exchangeCodeForAccessToken(
                redirectUri = "<RuName-value>",
                code = "<authorization-code-value>"
            )

            assert(response is NetworkResponse.Success)
            assertNotNull((response as NetworkResponse.Success).body)
        }
    }

    @Test
    fun `check refreshAccessToken`() {

        runBlocking {
            val ebay = createMockedInterface()

            val response = ebay.refreshAccessToken(
                refreshToken = "<refresh-token-value>",
                scopes = "<scope-value>"
            )

            assert(response is NetworkResponse.Success)
            assertNotNull((response as NetworkResponse.Success).body)
        }
    }

    @Test
    fun `check getApplicationToken`() {

        runBlocking {
            val ebay = createMockedInterface()

            val response = ebay.getApplicationToken(
                scopes = "<scope-value>"
            )

            if (response is NetworkResponse.UnknownError) {
                response.error.printStackTrace()
            }

            assert(response is NetworkResponse.Success)
            assertNotNull((response as NetworkResponse.Success).body)
        }
    }

    private fun createMockedInterface(): EBayOAuthInterface {

        val client = EBayOAuthClient(EBayCredentials("<app id>", "<cert id>", "<redirect uri>", Environment.SANDBOX))
        val retro = client.retroClient

        val mockRetrofit: Retromock = Retromock.Builder()
            .defaultBodyFactory(ResourceBodyFactory())
            .retrofit(retro)
            .build()

        return mockRetrofit.create(EBayOAuthInterface::class.java)
    }
}