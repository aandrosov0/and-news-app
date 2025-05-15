package com.dvx.news.ui.states

data class CategoryScreenUiState(
    val isLoading: Boolean = false,
    val articles: List<ArticleUiState> = listOf(),
    val error: ErrorUiState? = null
)