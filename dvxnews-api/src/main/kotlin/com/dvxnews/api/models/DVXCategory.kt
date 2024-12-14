package com.dvxnews.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DVXCategory(
    @SerialName("category_id") val id: Long,
    @SerialName("category_name") val name: String,
    val articles: List<DVXArticle>
)
