package dvx.news.app.states

import dvx.news.data.models.Category

data class CategoryUiState(
    val id: Long = 0,
    val name: String = "",
)

fun Category.toUiState() = CategoryUiState(
    id = id,
    name = name
)