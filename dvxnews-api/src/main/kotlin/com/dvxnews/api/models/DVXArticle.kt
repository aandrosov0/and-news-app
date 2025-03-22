package com.dvxnews.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DVXArticle(
    @SerialName("global_id") val id: Long,
    @SerialName("category_id") val categoryId: Long?,
    @SerialName("category_name") val categoryName: String,
    val subheadline: String,
    val headline: String,
    val slug: String?,
    @SerialName("lead_paragraph") val leadParagraph: String,
    val time: String,
    @SerialName("image_url") val imageUrl: String,
    @SerialName("square_image_url") val squareImageUrl: String,
)