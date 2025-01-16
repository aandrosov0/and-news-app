package dvx.news.app.states

data class ArticleScreenUiState(
    val isLoading: Boolean = false,
    val article: ArticleContentUiState = ArticleContentUiState(),
    val recommendedMiddleBlock: List<ArticleUiState> = emptyList(),
    val recommendedEndBlock: List<ArticleUiState> = emptyList(),
    val error: ErrorUiState? = null
)
