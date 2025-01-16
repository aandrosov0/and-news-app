package dvx.news.data.models

import com.dvxnews.api.models.DVXImage

data class ArticleContentImage(
    val id: Long,
    val type: Int,
    val url: String,
    val caption: String,
) : ArticleContentElement()

fun DVXImage.toArticleContentImage() = ArticleContentImage(
    id = id,
    type = type,
    url = url,
    caption = caption
)
