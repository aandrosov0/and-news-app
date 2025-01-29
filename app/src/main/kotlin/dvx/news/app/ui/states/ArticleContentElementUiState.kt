package dvx.news.app.ui.states

import dvx.news.data.models.ArticleContentElement
import dvx.news.data.models.ArticleContentImage
import dvx.news.data.models.ArticleContentText

sealed class ArticleContentElementUiState

fun ArticleContentElement.toUiState() = when (this) {
    is ArticleContentImage -> this.toUiState()
    is ArticleContentText -> this.toUiState()
}