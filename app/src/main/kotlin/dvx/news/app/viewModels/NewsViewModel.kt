package dvx.news.app.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dvx.news.app.R
import dvx.news.app.states.ErrorUiState
import dvx.news.app.states.NewsScreenUiState
import dvx.news.app.states.toUiState
import dvx.news.data.repositories.ArticlesRepository
import dvx.news.data.repositories.CategoriesRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okio.IOException

class NewsViewModel(
    private val articlesRepository: ArticlesRepository,
    private val categoriesRepository: CategoriesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(NewsScreenUiState())
    val uiState = _uiState.asStateFlow()

    private var allJob: Job? = null

    fun getAll(refresh: Boolean = false) {
        allJob?.cancel()
        allJob = viewModelScope.launch {
            _uiState.value = NewsScreenUiState(isLoading = true)

            _uiState.value = try {
                val recentArticles = articlesRepository.getRecent(refresh).map { it.toUiState() }
                val randomArticles = articlesRepository.getRandom(refresh).map { it.toUiState() }
                val categories = categoriesRepository.getAll(refresh).map { it.toUiState() }

                NewsScreenUiState(
                    recentArticles = recentArticles,
                    randomArticles = randomArticles,
                    categories = categories
                )
            } catch (_: IOException) {
                NewsScreenUiState(
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