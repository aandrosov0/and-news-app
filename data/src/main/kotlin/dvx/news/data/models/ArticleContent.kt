package dvx.news.data.models

import com.dvxnews.api.models.DVXArticleContent

data class ArticleContent(
    val id: Long = 0,
    val text: List<ArticleText>,
    val images: List<ArticleImage>
)

fun DVXArticleContent.toArticleContent() = ArticleContent(
    id = id,
    text = text.map { it.toArticleText() },
    images = images.map { it.toArticleImage() }
)