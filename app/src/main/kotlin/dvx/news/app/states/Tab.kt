package dvx.news.app.states

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import dvx.news.app.R

enum class Tab {
    ALL_NEWS,
    HEADERS,
}

val Tab.localizedName: String
    @Composable
    get() = stringResource(
        when (this) {
            Tab.ALL_NEWS -> R.string.all_news
            Tab.HEADERS -> R.string.headers
        }
    )
