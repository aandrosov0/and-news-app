package com.dvxnews.api.core

import com.dvxnews.api.exceptions.DVXException
import com.dvxnews.api.models.DVXResponse
import com.dvxnews.api.models.isSuccessful
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromStream
import okhttp3.Response

internal object ResponseInterceptorImpl : ResponseInterceptor {
    @OptIn(ExperimentalSerializationApi::class)
    override fun intercept(response: Response, serializer: Json): DVXResponse<JsonElement> {
        if (!response.isSuccessful) {
            throw DVXException("Response code is invalid: ${response.code}")
        }

        if (response.body == null) {
            throw DVXException("Response body is empty")
        }

        val body = serializer.decodeFromStream<DVXResponse<JsonElement>>(response.body!!.byteStream())

        if (!body.status.isSuccessful) {
            throw DVXException("Api error: ${body.body}")
        }

        return body
    }
}