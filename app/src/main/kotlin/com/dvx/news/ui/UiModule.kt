package com.dvx.news.ui

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.dvx.news.dataStore
import com.dvx.news.ui.viewModels.ArticleViewModel
import com.dvx.news.ui.viewModels.AudioViewModel
import com.dvx.news.ui.viewModels.CategoryViewModel
import com.dvx.news.ui.viewModels.HomeViewModel
import com.dvx.news.ui.viewModels.MainViewModel
import com.dvx.news.ui.viewModels.NewsViewModel
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

