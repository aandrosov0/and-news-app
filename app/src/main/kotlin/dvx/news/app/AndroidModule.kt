package dvx.news.app

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dvx.news.app.viewModels.AudioViewModel
import dvx.news.app.viewModels.HomeViewModel
import dvx.news.app.viewModels.SettingsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val androidModule = module {
    single<DataStore<Preferences>>{ androidContext().dataStore }
    viewModel { AudioViewModel() }
    viewModel { SettingsViewModel(settingsRepository = get()) }
    viewModel { HomeViewModel(get()) }
}