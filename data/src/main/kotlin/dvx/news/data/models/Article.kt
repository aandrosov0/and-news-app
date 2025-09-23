package dvx.news.data.models

import com.dvxnews.api.models.DVXArticle

data class Article(
    val id: String,
    val categoryId: String?,
    val categoryName: String,
    val subheadline: String,
    val headline: String,
    val slug: String?,
    val leadParagraph: String,
    val time: String,
    val imageUrl: String,
    val squareImageUrl: String,
)

fun DVXArticle.toArticle() = Article(
    id = id.toString(),
    categoryId = categoryId.toString(),
    categoryName = categoryName,
    subheadline = subheadline,
    headline = headline,
    slug = slug,
    leadParagraph = leadParagraph,
    time = time,
    imageUrl = imageUrl,
    squareImageUrl = squareImageUrl
)