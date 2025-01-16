package dvx.news.data.models

import com.dvxnews.api.models.DVXText

data class ArticleContentText(
    val id: Long,
    val type: ArticleContentTextType,
    val value: String
) : ArticleContentElement()

fun DVXText.toArticleContentText() = ArticleContentText(
    id = id,
    type = type.toArticleContentTextType(),
    value = value
)
