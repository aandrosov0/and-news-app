package com.dvxnews.api

import com.dvxnews.api.exceptions.DVXNewsException
import com.dvxnews.api.models.DVXArticleContent
import com.dvxnews.api.models.DVXResponse
import com.dvxnews.api.models.DVXArticles
import com.dvxnews.api.models.DVXCategory
import com.dvxnews.api.models.isSuccessful
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.jsonObject
import okhttp3.OkHttpClient
import okhttp3.Request

@OptIn(ExperimentalSerializationApi::class)
class DVXNewsClient(
    private val client: OkHttpClient = OkHttpClient(),
    private val serializer: Json = Json { ignoreUnknownKeys = true }
) {
    companion object {
        const val API_URL = "https://devapi.adlink.net"
        const val ARTICLE_URL = "$API_URL/websites/article"
        const val ARTICLES_URL = "$API_URL/websites/articles"
        const val CATEGORIES_URL = "$API_URL/websites/domain"
    }

    fun getArticles(
        domain: DVXNewsDomain = DVXNewsDomain.DVXNEWS,
        language: DVXNewsLanguage = DVXNewsLanguage.DE,
        categoryId: Long? = null,
    ): DVXArticles {
        var url = "$ARTICLES_URL?domain_name=$domain&language_id=$language"
        categoryId?.let { url += "&category_id=$categoryId" }

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

    fun getCategories(
        domain: DVXNewsDomain = DVXNewsDomain.DVXNEWS,
        language: DVXNewsLanguage = DVXNewsLanguage.DE
    ): List<DVXCategory> {
        val url = "$CATEGORIES_URL?domain_name=$domain&language_id=$language"
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

            val categoriesJson = result.body.jsonObject["categories"]
            if (categoriesJson == null) {
                throw DVXNewsException("Cannot fetch categories. Can't find json element")
            }

            return serializer.decodeFromJsonElement(categoriesJson)
        }
    }

    fun getArticle(
        id: Long,
        domain: DVXNewsDomain = DVXNewsDomain.DVXNEWS,
        language: DVXNewsLanguage = DVXNewsLanguage.DE
    ): DVXArticleContent {
        val url = "$ARTICLE_URL?domain_name=$domain&language_id=$language&article_id=$id"
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