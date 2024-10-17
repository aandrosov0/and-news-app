package dvx.news.app.states

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import dvx.news.app.R

enum class NewsTab {
    ALL_NEWS,
    HEADERS
}

val NewsTab.localizedName
    @Composable
    get() = stringResource(
        when (this) {
            NewsTab.ALL_NEWS -> R.string.all_news
            NewsTab.HEADERS -> R.string.headers
        }
    )

enum class Tab {
    REPRESENTATION,
    MESSAGES
}

val Tab.localizedName
    @Composable
    get() = stringResource(
        when (this) {
            Tab.REPRESENTATION -> R.string.representation
            Tab.MESSAGES -> R.string.messages
        }
    )
