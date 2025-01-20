package com.dvxnews.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DVXImage(
    @SerialName("global_id") val id: Long,
    @SerialName("midjourney_id") val midjourneyId: Long = 0,
    val type: Int,
    val url: String,
    val caption: String,
)
