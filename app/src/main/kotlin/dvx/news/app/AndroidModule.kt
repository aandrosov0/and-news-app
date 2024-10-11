package dvx.news.app

import android.content.Context
import dvx.news.app.viewModels.AudioViewModel
import dvx.news.app.viewModels.SettingsViewModel
import dvx.news.data.repositories.SettingsRepository
import dvx.news.data.repositories.SettingsRepositoryImpl
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

fun getAndroidModule(context: Context) = module {
    single<SettingsRepository> {
        SettingsRepositoryImpl(
            dataStore = context.dataStore
        )
    }

    viewModel { AudioViewModel() }
    viewModel { SettingsViewModel(settingsRepository = get()) }
}