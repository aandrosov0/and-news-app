package dvx.news.app.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dvx.news.app.states.OverviewScreenUiState
import dvx.news.app.states.toUiState
import dvx.news.data.repositories.CategoriesRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OverviewViewModel(
    private val categoriesRepository: CategoriesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(OverviewScreenUiState())
    val uiState = _uiState.asStateFlow()

    private var allJob: Job? = null
    fun getAll(refresh: Boolean = false) {
        allJob?.cancel()
        allJob = viewModelScope.launch {
            _uiState.value = OverviewScreenUiState(isLoading = true)
            val categories = categoriesRepository.getAll(refresh).map { it.toUiState() }
            _uiState.value = OverviewScreenUiState(categories = categories)
        }
    }
}