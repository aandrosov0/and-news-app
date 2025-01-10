package com.dvxnews.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DVXArticleContent(
    @SerialName("article_id") val id: Long,
    val captions: List<DVXCaption>,
    val faq: List<DVXFaq>,
    val meta: List<DVXMeta>,
    val promo: List<DVXPromo>,
    val text: List<DVXText>,
    val images: List<DVXImage>,
)
