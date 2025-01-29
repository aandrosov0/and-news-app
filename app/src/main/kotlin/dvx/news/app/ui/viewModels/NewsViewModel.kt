package dvx.news.app.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dvx.news.app.ui.core.ExceptionConverter
import dvx.news.app.ui.core.ViewModelExceptionConverter
import dvx.news.app.ui.states.ErrorUiState
import dvx.news.app.ui.states.NewsScreenUiState
import dvx.news.app.ui.states.toUiState
import dvx.news.data.repositories.ArticlesRepository
import dvx.news.data.repositories.CategoriesRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsViewModel(
    private val articlesRepository: ArticlesRepository,
    private val categoriesRepository: CategoriesRepository,
    private val exceptionConverter: ExceptionConverter<ErrorUiState> = ViewModelExceptionConverter
) : ViewModel() {
    private val _uiState = MutableStateFlow(NewsScreenUiState())
    val uiState = _uiState.asStateFlow()

    private var allJob: Job? = null

    fun getAll(refresh: Boolean = false) {
        allJob?.cancel()
        allJob = viewModelScope.launch {
            if (refresh) { _uiState.value = _uiState.value.copy(isLoading = true) }

            _uiState.value = try {
                val recentArticles = articlesRepository.getRecent(refresh).map { it.toUiState() }
                val randomArticles = articlesRepository.getRandom(refresh).map { it.toUiState() }
                val categories = categoriesRepository.getAll(refresh).map { it.toUiState() }

                NewsScreenUiState(
                    recentArticles = recentArticles,
                    randomArticles = randomArticles,
                    categories = categories
                )
            } catch (exception: Exception) {
                NewsScreenUiState(error = exceptionConverter.convert(exception))
            }
        }
    }
}