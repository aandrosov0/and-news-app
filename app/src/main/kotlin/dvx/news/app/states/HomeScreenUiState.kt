package dvx.news.app.states

data class HomeScreenUiState(
    val isLoading: Boolean = false,
    val recentArticles: List<ArticleUiState> = emptyList()
)
