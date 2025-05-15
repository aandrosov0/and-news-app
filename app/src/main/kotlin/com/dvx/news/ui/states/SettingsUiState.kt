package com.dvx.news.ui.states

import dvx.news.data.models.Settings

data class SettingsUiState(
    val theme: ThemeUiState = ThemeUiState.SYSTEM
)

fun SettingsUiState.asModel() = Settings(
    theme = theme.asModel()
)

fun Settings.asState() = SettingsUiState(
    theme = theme.asState()
)