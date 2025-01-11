package dvx.news.data.models

import com.dvxnews.api.models.DVXContentGroup

enum class ArticleContentGroup {
    CAPTIONS,
    FAQ,
    META,
    PROMO,
    TEXT
}

fun DVXContentGroup.toArticleContentGroup() = when (this) {
    DVXContentGroup.CAPTIONS -> ArticleContentGroup.CAPTIONS
    DVXContentGroup.FAQ -> ArticleContentGroup.FAQ
    DVXContentGroup.META -> ArticleContentGroup.META
    DVXContentGroup.PROMO -> ArticleContentGroup.PROMO
    DVXContentGroup.TEXT -> ArticleContentGroup.TEXT
}