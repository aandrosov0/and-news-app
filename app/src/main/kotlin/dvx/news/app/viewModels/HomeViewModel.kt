package dvx.news.app.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dvx.news.app.core.ExceptionConverter
import dvx.news.app.core.ViewModelExceptionConverter
import dvx.news.app.states.ErrorUiState
import dvx.news.app.states.HomeScreenUiState
import dvx.news.app.states.toUiState
import dvx.news.data.repositories.ArticlesRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val articlesRepository: ArticlesRepository,
    private val exceptionConverter: ExceptionConverter<ErrorUiState> = ViewModelExceptionConverter
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState = _uiState.asStateFlow()

    private var allJob: Job? = null

    fun getAll(refresh: Boolean = false) {
        allJob?.cancel()
        allJob = viewModelScope.launch {
            if (refresh) { _uiState.value = _uiState.value.copy(isLoading = true) }
            _uiState.value = try {
                val articles = articlesRepository.getRecent(refresh).map { it.toUiState() }
                HomeScreenUiState(recentArticles = articles)
            } catch (exception: Exception) {
                HomeScreenUiState(error = exceptionConverter.convert(exception))
            }
        }
    }
}