package dvx.news.app.states

data class CategoriesUiState(
    val categories: List<CategoryUiState> = listOf(),
    val isLoading: Boolean = false
)