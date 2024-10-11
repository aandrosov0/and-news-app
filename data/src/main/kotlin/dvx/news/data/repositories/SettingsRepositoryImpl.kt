package dvx.news.data.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import dvx.news.data.models.ExposedSettings
import dvx.news.data.models.ExposedTheme
import kotlinx.coroutines.flow.first

class SettingsRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : SettingsRepository {
    private val themePreferencesKey
        get() = stringPreferencesKey("theme")

    override suspend fun getSavedSettings(): ExposedSettings {
        val preferences = dataStore.data.first()
        val theme = preferences[themePreferencesKey]
        return ExposedSettings(
            exposedTheme = if (theme.isNullOrBlank()) ExposedTheme.SYSTEM else ExposedTheme.valueOf(theme)
        )
    }

    override suspend fun update(exposedSettings: ExposedSettings) {
        dataStore.edit {
            it[themePreferencesKey] = exposedSettings.exposedTheme.name
        }
    }
}