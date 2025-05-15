package com.dvx.news.ui.states

sealed interface ScreenState

data class DefaultScreenState(
    val error: ErrorUiState? = null,
    val onRefresh: () -> Unit = {},
) : ScreenState

data class RefreshableScreenState(
    val error: ErrorUiState? = null,
    val isRefreshing: Boolean = false,
    val onRefresh: () -> Unit = {},
) : ScreenState