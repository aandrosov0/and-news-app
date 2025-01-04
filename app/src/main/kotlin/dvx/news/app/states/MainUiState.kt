package dvx.news.app.states

data class MainUiState(
    val isLoaded: Boolean = false,
    val categories: List<CategoryUiState> = emptyList(),
    val error: ErrorUiState? = null
)
