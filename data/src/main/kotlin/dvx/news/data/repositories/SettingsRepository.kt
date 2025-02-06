package dvx.news.data.repositories

import dvx.news.data.models.Settings

interface SettingsRepository {
    suspend fun getSettings(): Settings
    suspend fun update(settings: Settings)
}