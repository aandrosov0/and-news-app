package dvx.news.app.states

import dvx.news.data.models.ExposedSettings

data class Settings(
    val theme: Theme = Theme.SYSTEM
)

fun Settings.asModel() = ExposedSettings(
    exposedTheme = theme.asModel()
)

fun ExposedSettings.asState() = Settings(
    theme = exposedTheme.asState()
)