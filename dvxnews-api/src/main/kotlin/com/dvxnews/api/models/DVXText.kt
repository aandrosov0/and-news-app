package com.dvxnews.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DVXText(
    @SerialName("global_id") val id: Long,
    @SerialName("content_group") val contentGroup: DVXContentGroup,
    val type: DVXTextType,
    val value: String,
)
