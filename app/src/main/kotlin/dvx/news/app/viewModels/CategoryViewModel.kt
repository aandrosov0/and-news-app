package dvx.news.app.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dvx.news.app.R
import dvx.news.app.states.CategoryScreenUiState
import dvx.news.app.states.ErrorUiState
import dvx.news.app.states.toUiState
import dvx.news.data.repositories.ArticlesRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okio.IOException

class CategoryViewModel(
    private val articlesRepository: ArticlesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(CategoryScreenUiState(isLoading = true))
    val uiState = _uiState.asStateFlow()

    private var getJob: Job? = null

    fun get(categoryId: Long) {
        getJob?.cancel()
        getJob = viewModelScope.launch {
            _uiState.value = CategoryScreenUiState(isLoading = true)
            _uiState.value = try {
                val articles = articlesRepository.getByCategory(categoryId).map { it.toUiState() }
                CategoryScreenUiState(articles = articles)
            } catch (_: IOException) {
                CategoryScreenUiState(
                    error = ErrorUiState(
                        messageId = R.string.no_network_error,
                        iconId = R.drawable.ic_signal_disconnected,
                        actionId = R.string.refresh_action,
                        onAction = { get(categoryId) }
                    )
                )
            }
        }
    }
}