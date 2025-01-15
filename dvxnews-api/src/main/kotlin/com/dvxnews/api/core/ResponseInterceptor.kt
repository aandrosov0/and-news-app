package com.dvxnews.api.core

import com.dvxnews.api.models.DVXResponse
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import okhttp3.Response

internal fun interface ResponseInterceptor {
    fun intercept(response: Response, serializer: Json): DVXResponse<JsonElement>
}