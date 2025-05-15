package com.dvx.news.ui.states

import androidx.annotation.Keep
import dvx.news.data.models.ArticleContentTextType

@Keep
enum class ArticleTextTypeUiState {
    SUBHEADLINE,
    HEADLINE,
    LEAD_PARAGRAPH,
    SUBHEADING,
    PARAGRAPH,
}

fun ArticleContentTextType.asState() = when (this) {
    ArticleContentTextType.SUBHEADLINE -> ArticleTextTypeUiState.SUBHEADLINE
    ArticleContentTextType.HEADLINE -> ArticleTextTypeUiState.HEADLINE
    ArticleContentTextType.LEAD_PARAGRAPH -> ArticleTextTypeUiState.LEAD_PARAGRAPH
    ArticleContentTextType.SUBHEADING -> ArticleTextTypeUiState.SUBHEADING
    ArticleContentTextType.PARAGRAPH -> ArticleTextTypeUiState.PARAGRAPH
}