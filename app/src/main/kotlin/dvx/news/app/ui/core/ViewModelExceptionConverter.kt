package dvx.news.app.ui.core

import dvx.news.app.R
import dvx.news.app.ui.states.ErrorUiState
import dvx.news.data.exceptions.DataLayerConnectionException

internal object ViewModelExceptionConverter : ExceptionConverter<ErrorUiState> {
    override fun convert(exception: Exception) = when (exception) {
        is DataLayerConnectionException -> ErrorUiState(
            messageId = R.string.no_network_error,
            iconId = R.drawable.ic_signal_disconnected,
            actionId = R.string.refresh_action,
        )
        else -> throw IllegalArgumentException("Exception is not applicable:\n$exception")
    }
}