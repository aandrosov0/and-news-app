package dvx.news.data.repositories

import dvx.news.data.models.ExposedSettings

interface SettingsRepository {
    suspend fun getSavedSettings(): ExposedSettings
    suspend fun update(exposedSettings: ExposedSettings)
}