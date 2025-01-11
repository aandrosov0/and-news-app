package dvx.news.data.models

import com.dvxnews.api.models.DVXTextType

enum class ArticleTextType {
    SUBHEADLINE,
    HEADLINE,
    LEAD_PARAGRAPH,
    SUBHEADING,
    PARAGRAPH,
}

fun DVXTextType.toArticleTextType() = when (this) {
    DVXTextType.SUBHEADLINE -> ArticleTextType.SUBHEADLINE
    DVXTextType.HEADLINE -> ArticleTextType.HEADLINE
    DVXTextType.LEAD_PARAGRAPH -> ArticleTextType.LEAD_PARAGRAPH
    DVXTextType.SUBHEADING -> ArticleTextType.SUBHEADING
    DVXTextType.PARAGRAPH -> ArticleTextType.PARAGRAPH
}
