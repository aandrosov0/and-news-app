package dvx.news.app.ui.states

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import dvx.news.app.R

enum class SettingsPage {
    REPRESENTATION,
    MESSAGES
}

val SettingsPage.localizedName: String
    @Composable get() = when (this) {
        SettingsPage.REPRESENTATION -> stringResource(R.string.representation)
        SettingsPage.MESSAGES -> stringResource(R.string.messages)
    }