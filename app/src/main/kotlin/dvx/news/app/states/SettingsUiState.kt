package dvx.news.app.states

import dvx.news.data.models.ExposedSettings

data class SettingsUiState(
    val theme: Theme = Theme.SYSTEM
)

fun SettingsUiState.asModel() = ExposedSettings(
    exposedTheme = theme.asModel()
)

fun ExposedSettings.toUiState() = SettingsUiState(
    theme = exposedTheme.toUiState()
)