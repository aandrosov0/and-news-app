package dvx.news.app.states

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.dataStore by preferencesDataStore(name = "settings")

data class Settings(
    val theme: Theme = Theme.SYSTEM
)

private val themePreferencesKey
    get() = stringPreferencesKey("theme")

suspend fun Context.getSavedSettings(): Settings {
    val preferences = dataStore.data.first()
    return Settings(
        theme = Theme.valueOf(preferences[themePreferencesKey] ?: Theme.SYSTEM.name)
    )
}

suspend fun Context.saveSettings(settings: Settings) {
    dataStore.edit {
        it[themePreferencesKey] = settings.theme.name
    }
}


