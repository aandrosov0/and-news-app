package dvx.news.data.models

import com.dvxnews.api.models.DVXTextType

enum class ArticleContentTextType {
    SUBHEADLINE,
    HEADLINE,
    LEAD_PARAGRAPH,
    SUBHEADING,
    PARAGRAPH,
}

fun DVXTextType.toArticleContentTextType() = when (this) {
    DVXTextType.SUBHEADLINE -> ArticleContentTextType.SUBHEADLINE
    DVXTextType.HEADLINE -> ArticleContentTextType.HEADLINE
    DVXTextType.LEAD_PARAGRAPH -> ArticleContentTextType.LEAD_PARAGRAPH
    DVXTextType.SUBHEADING -> ArticleContentTextType.SUBHEADING
    DVXTextType.PARAGRAPH -> ArticleContentTextType.PARAGRAPH
}
