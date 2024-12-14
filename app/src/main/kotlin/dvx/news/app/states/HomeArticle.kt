package dvx.news.app.states

import dvx.news.data.models.Article

data class HomeArticle(
    val id: Long = 0,
    val imageUrl: String,
)

fun Article.toHomeArticle() = HomeArticle(
    id = id,
    imageUrl = imageUrl
)
