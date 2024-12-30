package dvx.news.app.states

data class OverviewScreenUiState(
    val isLoading: Boolean = false,
    val categories: List<CategoryUiState> = emptyList()
)
