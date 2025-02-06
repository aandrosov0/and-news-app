package dvx.news.app.ui.states

import dvx.news.data.models.ArticleContent

data class ArticleContentUiState(
    val id: Long = 0,
    val elements: List<ArticleContentElementUiState> = emptyList()
)

fun ArticleContent.asState() = ArticleContentUiState(
    id = id,
    elements = elements.map { it.asState() }
)

