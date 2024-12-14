package com.dvxnews.api

import com.dvxnews.api.exceptions.DVXNewsException
import com.dvxnews.api.models.DVXResponse
import com.dvxnews.api.models.DVXArticles
import com.dvxnews.api.models.isSuccessful
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.decodeFromStream
import okhttp3.OkHttpClient
import okhttp3.Request

class DVXNewsClient(
    private val client: OkHttpClient = OkHttpClient(),
    private val serializer: Json = Json { ignoreUnknownKeys = true }
) {
    companion object {
        const val API_URL = "https://devapi.adlink.net"
        const val ARTICLES_API = "$API_URL/websites/articles"
    }

    @OptIn(ExperimentalSerializationApi::class)
    fun getArticles(
        domain: DVXNewsDomain = DVXNewsDomain.DVXNEWS,
        language: DVXNewsLanguage = DVXNewsLanguage.DE,
    ): DVXArticles {
        val url = "$ARTICLES_API?domain_name=$domain&language_id=$language"
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                throw DVXNewsException("Response code is invalid: ${response.code}")
            }

            if (response.body == null) {
                throw DVXNewsException("Response body is empty")
            }

            val result = serializer.decodeFromStream<DVXResponse<JsonElement>>(response.body!!.byteStream())

            if (!result.status.isSuccessful) {
                throw DVXNewsException("Api error: ${result.body}")
            }

            return serializer.decodeFromJsonElement(result.body)
        }
    }
}