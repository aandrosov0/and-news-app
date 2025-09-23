package dvx.news.data.models

data class ArticleContent(
    val id: String = "",
    val elements: List<ArticleContentElement> = emptyList()
)