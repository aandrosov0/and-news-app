package com.dvxnews.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DVXCategory(
    @SerialName("category_id") val id: Long,
    val name: String,
    val welcome: String,
)
