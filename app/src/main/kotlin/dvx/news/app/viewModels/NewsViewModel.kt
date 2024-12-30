package dvx.news.app.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dvx.news.app.states.NewsScreenUiState
import dvx.news.app.states.toUiState
import dvx.news.data.repositories.ArticlesRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsViewModel(
    private val articlesRepository: ArticlesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(NewsScreenUiState())
    val uiState = _uiState.asStateFlow()

    private var allJob: Job? = null

    fun getAll(refresh: Boolean = false) {
        allJob?.cancel()
        allJob = viewModelScope.launch {
            _uiState.value = NewsScreenUiState(isLoading = true)

            val recentArticles = articlesRepository.getRecent(refresh).map { it.toUiState() }
            val randomArticles = articlesRepository.getRandom(refresh).map { it.toUiState() }

            _uiState.value = NewsScreenUiState(
                recentArticles = recentArticles,
                randomArticles = randomArticles
            )
        }
    }
}