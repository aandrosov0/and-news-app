package dvx.news.data.models

data class ArticleContent(
    val id: Long = 0,
    val elements: List<ArticleContentElement> = emptyList()
)