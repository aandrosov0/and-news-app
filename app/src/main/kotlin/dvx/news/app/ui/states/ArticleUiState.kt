package dvx.news.app.ui.states

import dvx.news.data.models.Article

data class ArticleUiState(
    val id: Long = 0,
    val categoryId: Long? = null,
    val categoryName: String = "",
    val subheadline: String = "",
    val headline: String = "",
    val slug: String? = null,
    val leadParagraph: String = "",
    val time: String = "",
    val imageUrl: String = "",
    val squareImageUrl: String = "",
)

fun Article.asState() = ArticleUiState(
    id = id,
    categoryId = categoryId,
    categoryName = categoryName,
    subheadline = subheadline,
    headline = headline,
    slug = slug,
    leadParagraph = leadParagraph,
    time = time,
    imageUrl = imageUrl,
    squareImageUrl = squareImageUrl
)