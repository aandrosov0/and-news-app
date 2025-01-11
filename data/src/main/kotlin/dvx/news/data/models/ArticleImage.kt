package dvx.news.data.models

import com.dvxnews.api.models.DVXImage

data class ArticleImage(
    val id: Long,
    val type: Int,
    val url: String,
    val caption: String
)

fun DVXImage.toArticleImage() = ArticleImage(
    id = id,
    type = type,
    url = url,
    caption = caption
)
