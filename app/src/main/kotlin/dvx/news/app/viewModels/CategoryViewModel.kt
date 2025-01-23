package dvx.news.app.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dvx.news.app.core.ExceptionConverter
import dvx.news.app.core.ViewModelExceptionConverter
import dvx.news.app.states.CategoryScreenUiState
import dvx.news.app.states.ErrorUiState
import dvx.news.app.states.toUiState
import dvx.news.data.repositories.ArticlesRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val articlesRepository: ArticlesRepository,
    private val exceptionConverter: ExceptionConverter<ErrorUiState> = ViewModelExceptionConverter
) : ViewModel() {
    private val _uiState = MutableStateFlow(CategoryScreenUiState())
    val uiState = _uiState.asStateFlow()

    private var getJob: Job? = null

    fun get(categoryId: Long, refresh: Boolean = false) {
        getJob?.cancel()
        getJob = viewModelScope.launch {
            if (refresh) { _uiState.value = _uiState.value.copy(isLoading = true) }
            _uiState.value = try {
                val articles = articlesRepository.getByCategory(categoryId).map { it.toUiState() }
                CategoryScreenUiState(articles = articles)
            } catch (exception: Exception) {
                CategoryScreenUiState(error = exceptionConverter.convert(exception))
            }
        }
    }
}