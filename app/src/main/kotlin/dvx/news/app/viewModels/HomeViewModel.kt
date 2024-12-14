package dvx.news.app.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dvx.news.app.states.HomeArticles
import dvx.news.app.states.toHomeArticle
import dvx.news.data.models.Article
import dvx.news.data.repositories.ArticlesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val articlesRepository: ArticlesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeArticles())
    val uiState = _uiState.asStateFlow()

    private var gettingArticlesJob: Job? = null

    fun getArticles(refresh: Boolean = false) {
        gettingArticlesJob?.cancel()

        gettingArticlesJob = viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = HomeArticles(isLoading = true)

            val articles = articlesRepository
                .getRecent(refresh)
                .map(Article::toHomeArticle)

            _uiState.value = HomeArticles(articles = articles)
        }
    }
}