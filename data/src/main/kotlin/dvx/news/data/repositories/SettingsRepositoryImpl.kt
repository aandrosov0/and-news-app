package dvx.news.data.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import dvx.news.data.models.Settings
import dvx.news.data.models.Theme
import kotlinx.coroutines.flow.first

class SettingsRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : SettingsRepository {
    private val themePreferencesKey
        get() = stringPreferencesKey("theme")

    override suspend fun getSettings(): Settings {
        val preferences = dataStore.data.first()
        val theme = preferences[themePreferencesKey]
        return Settings(
            theme = if (theme.isNullOrBlank()) Theme.SYSTEM else Theme.valueOf(theme)
        )
    }

    override suspend fun update(settings: Settings) {
        dataStore.edit {
            it[themePreferencesKey] = settings.theme.name
        }
    }
}