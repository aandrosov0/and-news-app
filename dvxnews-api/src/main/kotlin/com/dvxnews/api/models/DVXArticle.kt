package com.dvxnews.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DVXArticle(
    @SerialName("global_id") val id: Long,
    @SerialName("category_id") val categoryId: Long,
    val subheadline: String,
    val headline: String,
    val slug: String?,
    @SerialName("lead_paragraph") val leadParagraph: String,
    val time: String,
    @SerialName("image_url") val imageUrl: String,
)