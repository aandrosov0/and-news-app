package com.dvx.news.ui.states

import dvx.news.data.models.ArticleContent

data class ArticleContentUiState(
    val id: String = "",
    val elements: List<ArticleContentElementUiState> = emptyList()
)

fun ArticleContent.asState() = ArticleContentUiState(
    id = id,
    elements = elements.map { it.asState() }
)

