package dvx.news.app.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dvx.news.app.R
import dvx.news.app.states.ErrorUiState
import dvx.news.app.states.MainUiState
import dvx.news.app.states.SettingsUiState
import dvx.news.app.states.asModel
import dvx.news.app.states.toUiState
import dvx.news.data.repositories.ArticlesRepository
import dvx.news.data.repositories.CategoriesRepository
import dvx.news.data.repositories.SettingsRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okio.IOException

class MainViewModel(
    private val categoriesRepository: CategoriesRepository,
    private val articlesRepository: ArticlesRepository,
    private val settingsRepository: SettingsRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState(isLoading = true))
    val uiState = _uiState.asStateFlow()

    private var loadJob: Job? = null
    private var updateSettingsJob: Job? = null

    fun load() {
        loadJob?.cancel()
        loadJob = viewModelScope.launch {
            _uiState.value = MainUiState(isLoading = true)

            _uiState.value = try {
                articlesRepository.getRecent(refresh = true)
                articlesRepository.getRandom(refresh = true)
                val categories = categoriesRepository.getAll(refresh = true).map { it.toUiState() }
                val settings = settingsRepository.getSavedSettings().toUiState()

                MainUiState(
                    categories = categories,
                    settings = settings,
                    onSettingsChange = { updateSettings(it) }
                )
            } catch (_: IOException) {
                MainUiState(
                    error = ErrorUiState(
                        messageId = R.string.no_network_error,
                        iconId = R.drawable.ic_signal_disconnected,
                        actionId = R.string.refresh_action,
                        onAction = { load() }
                    )
                )
            }
        }
    }

    fun updateSettings(settings: SettingsUiState) {
        updateSettingsJob?.cancel()
        updateSettingsJob = viewModelScope.launch {
            settingsRepository.update(settings.asModel())
            _uiState.value = _uiState.value.copy(settings = settings)
        }
    }
}