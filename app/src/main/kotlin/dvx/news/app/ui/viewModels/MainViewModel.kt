package dvx.news.app.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dvx.news.app.ui.core.ExceptionConverter
import dvx.news.app.ui.core.ViewModelExceptionConverter
import dvx.news.app.ui.states.ErrorUiState
import dvx.news.app.ui.states.SettingsUiState
import dvx.news.app.ui.states.asModel
import dvx.news.app.ui.states.asState
import dvx.news.app.ui.states.MainUiState
import dvx.news.data.repositories.ArticlesRepository
import dvx.news.data.repositories.CategoriesRepository
import dvx.news.data.repositories.SettingsRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val categoriesRepository: CategoriesRepository,
    private val articlesRepository: ArticlesRepository,
    private val settingsRepository: SettingsRepository,
    private val exceptionConverter: ExceptionConverter<ErrorUiState> = ViewModelExceptionConverter
) : ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState = _uiState.asStateFlow()

    private var loadJob: Job? = null
    private var updateSettingsJob: Job? = null

    fun load() {
        loadJob?.cancel()
        loadJob = viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            _uiState.value = try {
                articlesRepository.getRecent(refresh = true)
                articlesRepository.getRandom(refresh = true)
                val categories = categoriesRepository.getAll(refresh = true).map { it.asState() }
                val settings = settingsRepository.getSettings().asState()
                MainUiState(
                    categories = categories,
                    settings = settings,
                    onSettingsChange = { updateSettings(it) }
                )
            } catch (exception: Exception) {
                MainUiState(error = exceptionConverter.convert(exception))
            }
        }
    }

    private fun updateSettings(settings: SettingsUiState) {
        updateSettingsJob?.cancel()
        updateSettingsJob = viewModelScope.launch {
            settingsRepository.update(settings.asModel())
            _uiState.value = _uiState.value.copy(settings = settings)
        }
    }
}