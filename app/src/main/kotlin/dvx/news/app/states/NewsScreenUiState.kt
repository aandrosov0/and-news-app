package dvx.news.app.states

data class NewsScreenUiState(
    val isLoading: Boolean = false,
    val recentArticles: List<ArticleUiState> = listOf(),
    val randomArticles: List<ArticleUiState> = listOf(),
)
