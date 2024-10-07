package dvx.news.app.viewModels

import androidx.lifecycle.ViewModel
import dvx.news.data.repositories.SettingsRepository

class SettingsViewModel(
    private val settingsRepository: SettingsRepository
) : ViewModel() {
}