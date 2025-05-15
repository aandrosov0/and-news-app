package com.dvx.news.ui.states

data class NewsScreenUiState(
    val isLoading: Boolean = false,
    val recentArticles: List<ArticleUiState> = listOf(),
    val randomArticles: List<ArticleUiState> = listOf(),
    val categories: List<CategoryUiState> = listOf(),
    val error: ErrorUiState? = null
)
