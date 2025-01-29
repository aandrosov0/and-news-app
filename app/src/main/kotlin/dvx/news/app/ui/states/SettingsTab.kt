package dvx.news.app.ui.states

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import dvx.news.app.R

enum class SettingsTab : Tab {
    REPRESENTATION,
    MESSAGES;

    override val localizedName: String
        @Composable
        get() = stringResource(
            when (this) {
                REPRESENTATION -> R.string.representation
                MESSAGES -> R.string.messages
            }
        )
}
