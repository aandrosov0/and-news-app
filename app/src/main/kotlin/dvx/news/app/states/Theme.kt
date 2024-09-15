package dvx.news.app.states

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import dvx.news.app.R

enum class Theme {
    HEADLINES,
    BRIGHT,
    DARK
}

val Theme.localizedName: String
    @Composable
    get() = stringResource(
        when (this) {
            Theme.HEADLINES -> R.string.headlines
            Theme.BRIGHT -> R.string.bright
            Theme.DARK -> R.string.dark
        }
    )