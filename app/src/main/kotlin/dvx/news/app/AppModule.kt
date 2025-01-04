package dvx.news.app

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dvx.news.app.viewModels.AudioViewModel
import dvx.news.app.viewModels.HomeViewModel
import dvx.news.app.viewModels.MainViewModel
import dvx.news.app.viewModels.NewsViewModel
import dvx.news.app.viewModels.OverviewViewModel
import dvx.news.app.viewModels.SettingsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<DataStore<Preferences>>{ androidContext().dataStore }
    viewModel { AudioViewModel() }
    viewModel { SettingsViewModel(get()) }
    viewModel { NewsViewModel(get(), get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { OverviewViewModel(get()) }
    viewModel { MainViewModel(get(), get()) }
}

