package dvx.news.data

import dvx.news.data.repositories.SettingsRepository
import dvx.news.data.repositories.SettingsRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single<SettingsRepository> {
        SettingsRepositoryImpl(
            dataStore = get()
        )
    }
}