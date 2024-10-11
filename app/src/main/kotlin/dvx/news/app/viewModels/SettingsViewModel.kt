package dvx.news.app.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dvx.news.app.states.Settings
import dvx.news.app.states.asModel
import dvx.news.app.states.asState
import dvx.news.data.repositories.SettingsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val settingsRepository: SettingsRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {
    private val _state = MutableStateFlow(Settings())
    val state = _state.asStateFlow()

    private var updatingJob: Job? = null
    private var gettingSavedJob: Job? = null

    fun update(settings: Settings) {
        updatingJob?.cancel()
        updatingJob = viewModelScope.launch(dispatcher) {
            settingsRepository.update(settings.asModel())
            _state.value = settings
        }
    }

    fun getSaved() {
        gettingSavedJob?.cancel()
        gettingSavedJob = viewModelScope.launch(dispatcher) {
            _state.value = settingsRepository.getSavedSettings().asState()
        }
    }
}