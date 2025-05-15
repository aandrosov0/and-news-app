package com.dvx.news.ui.states

import androidx.annotation.Keep
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.dvx.news.R

@Keep
enum class NewsTab {
    ALL_NEWS,
    HEADERS
}

val NewsTab.localizedName: String
    @Composable get() = when (this) {
        NewsTab.ALL_NEWS -> stringResource(R.string.all_news)
        NewsTab.HEADERS -> stringResource(R.string.headers)
    }
