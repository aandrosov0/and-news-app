package dvx.news.app.states

data class ArticleScreenUiState(
    val isLoading: Boolean = false,
    val article: ArticleContentUiState = ArticleContentUiState(),
    val random: List<ArticleUiState> = emptyList(),
    val error: ErrorUiState? = null
)
