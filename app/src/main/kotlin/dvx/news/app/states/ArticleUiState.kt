package dvx.news.app.states

import dvx.news.data.models.Article

data class ArticleUiState(
    val id: Long = 0,
    val categoryId: Long = 0,
    val subheadline: String = "",
    val headline: String = "",
    val slug: String? = null,
    val leadParagraph: String = "",
    val time: String = "",
    val imageUrl: String = "",
)

fun Article.toUiState() = ArticleUiState(
    id = id,
    categoryId = categoryId,
    subheadline = subheadline,
    headline = headline,
    slug = slug,
    leadParagraph = leadParagraph,
    time = time,
    imageUrl = imageUrl
)