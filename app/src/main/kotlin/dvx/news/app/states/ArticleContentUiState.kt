package dvx.news.app.states

import dvx.news.data.models.ArticleContent
import dvx.news.data.models.ArticleContentGroup
import dvx.news.data.models.ArticleImage
import dvx.news.data.models.ArticleText
import dvx.news.data.models.ArticleTextType

data class ArticleContentUiState(
    val id: Long = 0,
    val text: List<ArticleTextUiState> = emptyList(),
    val images: List<ArticleImageUiState> = emptyList()
)

fun ArticleContent.toUiState() = ArticleContentUiState(
    id = id,
    text = text.map { it.toUiState() },
    images = images.map { it.toUiState() }
)

data class ArticleTextUiState(
    val id: Long = 0,
    val contentGroup: ArticleContentGroupUiState = ArticleContentGroupUiState.TEXT,
    val type: ArticleTextTypeUiState = ArticleTextTypeUiState.LEAD_PARAGRAPH,
    val value: String
)

fun ArticleText.toUiState() = ArticleTextUiState(
    id = id,
    contentGroup = contentGroup.toUiState(),
    type = type.toUiState(),
    value = value
)

data class ArticleImageUiState(
    val id: Long,
    val type: Int,
    val url: String,
    val caption: String
)

fun ArticleImage.toUiState() = ArticleImageUiState(
    id = id,
    type = type,
    url = url,
    caption = caption
)

enum class ArticleContentGroupUiState {
    CAPTIONS,
    FAQ,
    META,
    PROMO,
    TEXT
}

fun ArticleContentGroup.toUiState() = when (this) {
    ArticleContentGroup.CAPTIONS -> ArticleContentGroupUiState.CAPTIONS
    ArticleContentGroup.FAQ -> ArticleContentGroupUiState.FAQ
    ArticleContentGroup.META -> ArticleContentGroupUiState.META
    ArticleContentGroup.PROMO -> ArticleContentGroupUiState.PROMO
    ArticleContentGroup.TEXT -> ArticleContentGroupUiState.TEXT
}

enum class ArticleTextTypeUiState {
    SUBHEADLINE,
    HEADLINE,
    LEAD_PARAGRAPH,
    SUBHEADING,
    PARAGRAPH,
}

fun ArticleTextType.toUiState() = when (this) {
    ArticleTextType.SUBHEADLINE -> ArticleTextTypeUiState.SUBHEADLINE
    ArticleTextType.HEADLINE -> ArticleTextTypeUiState.HEADLINE
    ArticleTextType.LEAD_PARAGRAPH -> ArticleTextTypeUiState.LEAD_PARAGRAPH
    ArticleTextType.SUBHEADING -> ArticleTextTypeUiState.SUBHEADING
    ArticleTextType.PARAGRAPH -> ArticleTextTypeUiState.PARAGRAPH
}