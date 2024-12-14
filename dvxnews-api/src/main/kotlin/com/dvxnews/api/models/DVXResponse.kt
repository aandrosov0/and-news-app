package com.dvxnews.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class DVXResponse<T>(
    val status: DVXNewsStatus,
    @SerialName("execution-time") val executionTime: String,
    val body: T
)
