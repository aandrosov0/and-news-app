package dvx.news.app.states

import dvx.news.data.models.ArticleContentTextType

enum class ArticleTextTypeUiState {
    SUBHEADLINE,
    HEADLINE,
    LEAD_PARAGRAPH,
    SUBHEADING,
    PARAGRAPH,
}

fun ArticleContentTextType.toUiState() = when (this) {
    ArticleContentTextType.SUBHEADLINE -> ArticleTextTypeUiState.SUBHEADLINE
    ArticleContentTextType.HEADLINE -> ArticleTextTypeUiState.HEADLINE
    ArticleContentTextType.LEAD_PARAGRAPH -> ArticleTextTypeUiState.LEAD_PARAGRAPH
    ArticleContentTextType.SUBHEADING -> ArticleTextTypeUiState.SUBHEADING
    ArticleContentTextType.PARAGRAPH -> ArticleTextTypeUiState.PARAGRAPH
}