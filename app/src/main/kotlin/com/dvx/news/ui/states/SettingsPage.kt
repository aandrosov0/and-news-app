package com.dvx.news.ui.states

import androidx.annotation.Keep
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.dvx.news.R

@Keep
enum class SettingsPage {
    REPRESENTATION,
    MESSAGES
}

val SettingsPage.localizedName: String
    @Composable get() = when (this) {
        SettingsPage.REPRESENTATION -> stringResource(R.string.representation)
        SettingsPage.MESSAGES -> stringResource(R.string.messages)
    }