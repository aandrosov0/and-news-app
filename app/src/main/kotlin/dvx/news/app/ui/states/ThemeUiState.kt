package dvx.news.app.ui.states

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import dvx.news.app.R
import dvx.news.data.models.Theme

enum class ThemeUiState {
    SYSTEM,
    BRIGHT,
    DARK
}

val ThemeUiState.localizedName: String
    @Composable
    get() = stringResource(
        when (this) {
            ThemeUiState.SYSTEM -> R.string.headlines
            ThemeUiState.BRIGHT -> R.string.bright
            ThemeUiState.DARK -> R.string.dark
        }
    )

fun ThemeUiState.asModel() = when (this) {
    ThemeUiState.SYSTEM -> Theme.SYSTEM
    ThemeUiState.BRIGHT -> Theme.BRIGHT
    ThemeUiState.DARK -> Theme.DARK
}

fun Theme.asState() = when (this) {
    Theme.SYSTEM -> ThemeUiState.SYSTEM
    Theme.BRIGHT -> ThemeUiState.BRIGHT
    Theme.DARK -> ThemeUiState.DARK
}