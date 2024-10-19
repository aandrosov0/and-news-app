package dvx.news.app.states

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import dvx.news.app.R

enum class NewsTab : Tab {
    ALL_NEWS,
    HEADERS;

    override val localizedName: String
        @Composable
        get() = stringResource(
            when (this) {
                ALL_NEWS -> R.string.all_news
                HEADERS -> R.string.headers
            }
        )
}
