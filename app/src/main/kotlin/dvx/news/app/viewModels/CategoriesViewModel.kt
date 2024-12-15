package dvx.news.app.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dvx.news.app.states.CategoriesUiState
import dvx.news.app.states.toUiState
import dvx.news.data.models.Category
import dvx.news.data.repositories.CategoriesRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CategoriesViewModel(
    private val categoriesRepository: CategoriesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(CategoriesUiState())
    val uiState = _uiState.asStateFlow()

    private var gettingAllJob: Job? = null

    fun getAll(refresh: Boolean = false) {
        gettingAllJob?.cancel()

        gettingAllJob = viewModelScope.launch {
            _uiState.value = CategoriesUiState(isLoading = true)
            val categories = categoriesRepository.getAll(refresh).map(Category::toUiState)
            _uiState.value = CategoriesUiState(categories = categories)
        }
    }
}