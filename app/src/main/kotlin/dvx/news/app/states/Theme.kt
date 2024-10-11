package dvx.news.app.states

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import dvx.news.app.R
import dvx.news.data.models.ExposedTheme

enum class Theme {
    SYSTEM,
    BRIGHT,
    DARK
}

val Theme.localizedName: String
    @Composable
    get() = stringResource(
        when (this) {
            Theme.SYSTEM -> R.string.headlines
            Theme.BRIGHT -> R.string.bright
            Theme.DARK -> R.string.dark
        }
    )

fun Theme.asModel() = when (this) {
    Theme.SYSTEM -> ExposedTheme.SYSTEM
    Theme.BRIGHT -> ExposedTheme.BRIGHT
    Theme.DARK -> ExposedTheme.DARK
}

fun ExposedTheme.asState() = when (this) {
    ExposedTheme.SYSTEM -> Theme.SYSTEM
    ExposedTheme.BRIGHT -> Theme.BRIGHT
    ExposedTheme.DARK -> Theme.DARK
}