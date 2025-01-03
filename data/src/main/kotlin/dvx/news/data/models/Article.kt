package dvx.news.data.models

import com.dvxnews.api.models.DVXArticle

data class Article(
    val id: Long,
    val categoryId: Long,
    val subheadline: String,
    val headline: String,
    val slug: String?,
    val leadParagraph: String,
    val time: String,
    val imageUrl: String,
)

fun DVXArticle.toArticle() = Article(
    id = id,
    categoryId = categoryId,
    subheadline = subheadline,
    headline = headline,
    slug = slug,
    leadParagraph = leadParagraph,
    time = time,
    imageUrl = imageUrl,
)