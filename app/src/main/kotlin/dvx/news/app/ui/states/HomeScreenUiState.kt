package dvx.news.app.ui.states

data class HomeScreenUiState(
    val isLoading: Boolean = false,
    val error: ErrorUiState? = null,
    val recentArticles: List<ArticleUiState> = emptyList()
)
