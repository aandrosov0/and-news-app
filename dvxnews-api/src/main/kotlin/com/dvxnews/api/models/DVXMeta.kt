package com.dvxnews.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DVXMeta(
    @SerialName("global_id") val id: Long,
    @SerialName("content_group") val contentGroup: DVXContentGroup,
    val type: String,
    val value: String
)
