package com.dvx.news.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dvx.news.ui.core.ExceptionConverter
import com.dvx.news.ui.core.ViewModelExceptionConverter
import com.dvx.news.ui.states.ErrorUiState
import com.dvx.news.ui.states.NewsScreenUiState
import com.dvx.news.ui.states.asState
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
                val categories = categoriesRepository.getAll(refresh).map { it.asState() }
                val recentArticles = articlesRepository.getRecent(refresh).map { article ->
                    article.asState().copy(
                        categoryName = categories.firstOrNull { it.id == article.categoryId }?.name ?: ""
                    )
                }
                val randomArticles = articlesRepository.getRandom(refresh).map { article ->
                    article.asState().copy(
                        categoryName = categories.firstOrNull { it.id == article.categoryId }?.name ?: ""
                    )
                }

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