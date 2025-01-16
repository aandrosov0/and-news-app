package dvx.news.app.states

import dvx.news.data.models.ArticleContentText

data class ArticleContentTextUiState(
    val id: Long = 0,
    val type: ArticleTextTypeUiState = ArticleTextTypeUiState.LEAD_PARAGRAPH,
    val value: String
) : ArticleContentElementUiState()

fun ArticleContentText.toUiState() = ArticleContentTextUiState(
    id = id,
    type = type.toUiState(),
    value = value
)