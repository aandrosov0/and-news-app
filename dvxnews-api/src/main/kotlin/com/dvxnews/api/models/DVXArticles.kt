package com.dvxnews.api.models

import kotlinx.serialization.Serializable

@Serializable
data class DVXArticles(
    val recent: List<DVXArticle> = emptyList(),
    val random: List<DVXArticle> = emptyList(),
    val categories: List<DVXCategory> = emptyList(),
)
