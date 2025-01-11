package dvx.news.data.models

import com.dvxnews.api.models.DVXText

data class ArticleText(
    val id: Long,
    val contentGroup: ArticleContentGroup,
    val type: ArticleTextType,
    val value: String
)

fun DVXText.toArticleText() = ArticleText(
    id = id,
    contentGroup = contentGroup.toArticleContentGroup(),
    type = type.toArticleTextType(),
    value = value
)
