package dvx.news.app.states

data class HomeArticles(
    val articles: List<HomeArticle> = emptyList(),
    val isLoading: Boolean = false,
)
