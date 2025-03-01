package com.dvxnews.api

import com.dvxnews.api.core.ExceptionInterceptor
import com.dvxnews.api.core.ExceptionInterceptorImpl
import com.dvxnews.api.core.ResponseInterceptor
import com.dvxnews.api.core.ResponseInterceptorImpl
import com.dvxnews.api.models.DVXArticleContent
import com.dvxnews.api.models.DVXArticles
import com.dvxnews.api.models.DVXCategory
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonObject
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.IOException

class DVXNewsClient(
    private val client: OkHttpClient = OkHttpClient(),
    private val serializer: Json = Json { ignoreUnknownKeys = true }
) {
    private val responseInterceptor: ResponseInterceptor = ResponseInterceptorImpl
    private val exceptionInterceptor: ExceptionInterceptor = ExceptionInterceptorImpl

    companion object {
        private const val API_URL = "https://api.adlink.net"
        const val ARTICLE_URL = "$API_URL/websites/article"
        const val ARTICLES_URL = "$API_URL/websites/articles"
        const val CATEGORIES_URL = "$API_URL/websites/domain"
    }

    fun getArticles(
        domain: DVXNewsDomain = DVXNewsDomain.DVXNEWS,
        language: DVXNewsLanguage = DVXNewsLanguage.DE,
        skipDomainCheck: Boolean = false,
        categoryId: Long? = null,
    ): DVXArticles {
        var url = "$ARTICLES_URL?domain_name=$domain&language_id=$language&skip_domain_check=$skipDomainCheck"
        categoryId?.let { url += "&category_id=$categoryId" }
        val request = Request.Builder()
            .url(url)
            .build()
        
        println(url)

        try {
            client.newCall(request).execute().use { response ->
                val result = responseInterceptor.intercept(response, serializer)
                return serializer.decodeFromJsonElement(result.body)
            }
        } catch (exception: IOException) {
            throw exceptionInterceptor.intercept(exception)
        }
    }

    fun getCategories(
        domain: DVXNewsDomain = DVXNewsDomain.DVXNEWS,
        language: DVXNewsLanguage = DVXNewsLanguage.DE,
        skipDomainCheck: Boolean = false,
    ): List<DVXCategory> {
        val url = "$CATEGORIES_URL?domain_name=$domain&language_id=$language&skip_domain_check=$skipDomainCheck"
        val request = Request.Builder()
            .url(url)
            .build()

        try {
            client.newCall(request).execute().use { response ->
                val result = responseInterceptor.intercept(response, serializer)
                val categoriesJson = result.body.jsonObject["categories"]!!
                return serializer.decodeFromJsonElement(categoriesJson)
            }
        } catch (exception: IOException) {
            throw exceptionInterceptor.intercept(exception)
        }
    }

    fun getArticle(
        id: Long,
        domain: DVXNewsDomain = DVXNewsDomain.DVXNEWS,
        language: DVXNewsLanguage = DVXNewsLanguage.DE,
        skipDomainCheck: Boolean = false,
    ): DVXArticleContent {
        val url = "$ARTICLE_URL?domain_name=$domain&language_id=$language&article_id=$id&skip_domain_check=$skipDomainCheck"
        println(url)
        val request = Request.Builder()
            .url(url)
            .build()

        try {
            client.newCall(request).execute().use { response ->
                val result = responseInterceptor.intercept(response, serializer)
                return serializer.decodeFromJsonElement(result.body)
            }
        } catch (exception: IOException) {
            throw exceptionInterceptor.intercept(exception)
        }
    }
}