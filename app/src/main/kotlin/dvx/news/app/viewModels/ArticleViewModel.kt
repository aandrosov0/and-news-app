package dvx.news.app.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dvx.news.app.core.ExceptionConverter
import dvx.news.app.core.ViewModelExceptionConverter
import dvx.news.app.states.ArticleScreenUiState
import dvx.news.app.states.ErrorUiState
import dvx.news.app.states.toUiState
import dvx.news.data.repositories.ArticlesRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ArticleViewModel(
    private val articlesRepository: ArticlesRepository,
    private val exceptionConverter: ExceptionConverter<ErrorUiState> = ViewModelExceptionConverter
) : ViewModel() {
    private val _uiState = MutableStateFlow(ArticleScreenUiState())
    val uiState = _uiState.asStateFlow()

    private var getArticleJob: Job? = null

    fun getArticle(id: Long) {
        getArticleJob?.cancel()
        getArticleJob = viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            _uiState.value = try {
                val random = articlesRepository.getRandom().map { it.toUiState() }
                val article = articlesRepository.getArticle(id).toUiState()
                ArticleScreenUiState(
                    article = article,
                    random = random
                )
            } catch (exception: Exception) {
                ArticleScreenUiState(error = exceptionConverter.convert(exception))
            }
        }
    }
}