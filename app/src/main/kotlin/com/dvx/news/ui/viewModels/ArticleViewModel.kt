package com.dvx.news.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dvx.news.ui.core.ExceptionConverter
import com.dvx.news.ui.core.ViewModelExceptionConverter
import com.dvx.news.ui.states.ArticleScreenUiState
import com.dvx.news.ui.states.ErrorUiState
import com.dvx.news.ui.states.asState
import dvx.news.data.exceptions.DataLayerException
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

    fun getArticle(id: Long, refresh: Boolean = false) {
        getArticleJob?.cancel()
        getArticleJob = viewModelScope.launch {
            if (refresh) { _uiState.value = _uiState.value.copy(isLoading = true) }
            _uiState.value = try {
                val random = articlesRepository.getRandom(refresh).map { it.asState() }
                val article = articlesRepository.getArticle(id).asState()
                ArticleScreenUiState(
                    article = article,
                    recommendedMiddleBlock = random.take(2),
                    recommendedEndBlock = random.takeLast(4)
                )
            } catch (exception: DataLayerException) {
                ArticleScreenUiState(error = exceptionConverter.convert(exception))
            }
        }
    }
}