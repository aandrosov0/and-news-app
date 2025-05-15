package com.dvx.news.ui.states

import dvx.news.data.models.ArticleContentElement
import dvx.news.data.models.ArticleContentImage
import dvx.news.data.models.ArticleContentText

sealed class ArticleContentElementUiState

fun ArticleContentElement.asState() = when (this) {
    is ArticleContentImage -> this.asState()
    is ArticleContentText -> this.asState()
}