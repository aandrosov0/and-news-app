package dvx.news.app.ui.states

data class MainUiState(
    val isLoading: Boolean = false,
    val categories: List<CategoryUiState> = emptyList(),
    val error: ErrorUiState? = null,
    val settings: SettingsUiState = SettingsUiState(),
    val onSettingsChange: (SettingsUiState) -> Unit = {}
)
