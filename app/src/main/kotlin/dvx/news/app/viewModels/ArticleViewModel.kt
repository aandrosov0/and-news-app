package dvx.news.app.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dvx.news.app.R
import dvx.news.app.states.ArticleScreenUiState
import dvx.news.app.states.ErrorUiState
import dvx.news.app.states.toUiState
import dvx.news.data.repositories.ArticlesRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okio.IOException

class ArticleViewModel(
    private val articlesRepository: ArticlesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ArticleScreenUiState())
    val uiState = _uiState.asStateFlow()

    private var getArticleJob: Job? = null

    fun getArticle(id: Long) {
        getArticleJob?.cancel()
        getArticleJob = viewModelScope.launch {
            _uiState.value = ArticleScreenUiState(isLoading = true)
            _uiState.value = try {
                val article = articlesRepository.getArticle(id).toUiState()
                ArticleScreenUiState(article = article)
            } catch (_: IOException) {
                ArticleScreenUiState(
                    error = ErrorUiState(
                        messageId = R.string.no_network_error,
                        iconId = R.drawable.ic_signal_disconnected,
                        actionId = R.string.refresh_action,
                        onAction = { getArticle(id) }
                    )
                )
            }
        }
    }
}