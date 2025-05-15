package com.dvx.news.ui.core

import com.dvx.news.ui.states.ErrorUiState
import com.dvx.news.R
import dvx.news.data.exceptions.DataLayerConnectionException

internal object ViewModelExceptionConverter : ExceptionConverter<ErrorUiState> {
    override fun convert(exception: Exception) = when (exception) {
        is DataLayerConnectionException -> ErrorUiState(
            messageId = R.string.no_network_error,
            iconId = R.drawable.ic_wifi_off,
            actionId = R.string.refresh_action,
        )
        else -> throw IllegalArgumentException("Exception is not applicable:\n$exception")
    }
}