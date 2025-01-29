package dvx.news.app.ui

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dvx.news.app.dataStore
import dvx.news.app.ui.viewModels.ArticleViewModel
import dvx.news.app.ui.viewModels.AudioViewModel
import dvx.news.app.ui.viewModels.CategoryViewModel
import dvx.news.app.ui.viewModels.HomeViewModel
import dvx.news.app.ui.viewModels.MainViewModel
import dvx.news.app.ui.viewModels.NewsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    single<DataStore<Preferences>> { androidContext().dataStore }

    single { MainViewModel(get(), get(), get()) }
    viewModel { AudioViewModel() }
    viewModel { NewsViewModel(get(), get()) }
    viewModel { HomeViewModel(articlesRepository = get()) }
    viewModel { CategoryViewModel(get()) }
    viewModel { ArticleViewModel(get()) }
}

