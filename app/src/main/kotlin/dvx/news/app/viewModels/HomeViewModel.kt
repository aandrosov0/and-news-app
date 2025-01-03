package dvx.news.app.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dvx.news.app.states.ErrorUiState
import dvx.news.app.states.HomeScreenUiState
import dvx.news.app.states.toUiState
import dvx.news.data.repositories.ArticlesRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import dvx.news.app.R
import okio.IOException

class HomeViewModel(
    private val articlesRepository: ArticlesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState = _uiState.asStateFlow()

    private var allJob: Job? = null

    fun getAll(refresh: Boolean = false) {
        allJob?.cancel()
        allJob = viewModelScope.launch {
            _uiState.value = HomeScreenUiState(isLoading = true)
            _uiState.value = try {
                val articles = articlesRepository.getRecent(refresh).map { it.toUiState() }
                HomeScreenUiState(recentArticles = articles)
            } catch (_: IOException) {
                HomeScreenUiState(
                    error = ErrorUiState(
                        messageId = R.string.no_network_error,
                        iconId = R.drawable.ic_signal_disconnected,
                        actionId = R.string.refresh_action,
                        onAction = { getAll(true) }
                    )
                )
            }
        }
    }
}