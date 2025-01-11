package dvx.news.app

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dvx.news.app.viewModels.ArticleViewModel
import dvx.news.app.viewModels.AudioViewModel
import dvx.news.app.viewModels.CategoryViewModel
import dvx.news.app.viewModels.HomeViewModel
import dvx.news.app.viewModels.MainViewModel
import dvx.news.app.viewModels.NewsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single<DataStore<Preferences>> { androidContext().dataStore }

    viewModel { AudioViewModel() }
    viewModelOf(::NewsViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::MainViewModel)
    viewModelOf(::CategoryViewModel)
    viewModelOf(::ArticleViewModel)
}

