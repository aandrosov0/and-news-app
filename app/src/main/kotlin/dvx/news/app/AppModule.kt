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
import org.koin.dsl.module

val appModule = module {
    single<DataStore<Preferences>> { androidContext().dataStore }

    viewModel { AudioViewModel() }
    viewModel { NewsViewModel(get(), get()) }
    viewModel { HomeViewModel(articlesRepository = get()) }
    viewModel { MainViewModel(get(), get(), get()) }
    viewModel { CategoryViewModel(get()) }
    viewModel { ArticleViewModel(get()) }
}

