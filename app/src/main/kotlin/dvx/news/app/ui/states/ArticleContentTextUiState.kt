package dvx.news.app.ui.states

import dvx.news.data.models.ArticleContentText

data class ArticleContentTextUiState(
    val id: Long = 0,
    val type: ArticleTextTypeUiState = ArticleTextTypeUiState.LEAD_PARAGRAPH,
    val value: String
) : ArticleContentElementUiState()

fun ArticleContentText.asState() = ArticleContentTextUiState(
    id = id,
    type = type.asState(),
    value = value
)