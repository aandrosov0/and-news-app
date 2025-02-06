package dvx.news.app.ui.states

import dvx.news.data.models.ArticleContentImage

data class ArticleContentImageUiState(
    val id: Long,
    val type: Int,
    val url: String,
    val caption: String
) : ArticleContentElementUiState()

fun ArticleContentImage.asState() = ArticleContentImageUiState(
    id = id,
    type = type,
    url = url,
    caption = caption
)