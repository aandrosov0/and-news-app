package dvx.news.app.states

data class HomeScreenUiState(
    val isLoading: Boolean = false,
    val error: ErrorUiState? = null,
    val recentArticles: List<ArticleUiState> = emptyList()
)
