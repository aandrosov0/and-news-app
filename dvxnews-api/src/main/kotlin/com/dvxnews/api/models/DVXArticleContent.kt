package com.dvxnews.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DVXArticleContent(
    @SerialName("article_id") val id: Long,
    val captions: List<DVXCaption> = listOf(),
    val faq: List<DVXFaq> = listOf(),
    val meta: List<DVXMeta> = listOf(),
    val promo: List<DVXPromo> = listOf(),
    val text: List<DVXText>,
    val images: List<DVXImage>,
)
