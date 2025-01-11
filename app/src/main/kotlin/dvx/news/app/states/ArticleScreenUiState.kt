package dvx.news.app.states

data class ArticleScreenUiState(
    val isLoading: Boolean = false,
    val article: ArticleContentUiState = ArticleContentUiState(),
    val error: ErrorUiState? = null
)
