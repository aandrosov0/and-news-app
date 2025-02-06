package dvx.news.app.ui.states

import dvx.news.data.models.Article

data class ArticleUiState(
    val id: Long = 0,
    val categoryId: Long? = null,
    val subheadline: String = "",
    val headline: String = "",
    val slug: String? = null,
    val leadParagraph: String = "",
    val time: String = "",
    val imageUrl: String = "",
)

fun Article.asState() = ArticleUiState(
    id = id,
    categoryId = categoryId,
    subheadline = subheadline,
    headline = headline,
    slug = slug,
    leadParagraph = leadParagraph,
    time = time,
    imageUrl = imageUrl
)