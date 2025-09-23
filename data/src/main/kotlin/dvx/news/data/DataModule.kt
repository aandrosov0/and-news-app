package dvx.news.data

import com.dvxnews.api.DVXNewsClient
import dvx.news.data.appwrite.Appwrite
import dvx.news.data.appwrite.repositories.AppwriteCategoriesRepository
import dvx.news.data.appwrite.repositories.AppwriteNewsRepository
import dvx.news.data.dataSources.ArticlesDataSource
import dvx.news.data.dataSources.ArticlesRemoteDataSource
import dvx.news.data.dataSources.CategoriesDataSource
import dvx.news.data.dataSources.CategoriesRemoteDataSource
import dvx.news.data.repositories.ArticlesFakeRepository
import dvx.news.data.repositories.ArticlesRepository
import dvx.news.data.repositories.ArticlesRepositoryImpl
import dvx.news.data.repositories.CategoriesFakeRepository
import dvx.news.data.repositories.CategoriesRepository
import dvx.news.data.repositories.CategoriesRepositoryImpl
import dvx.news.data.repositories.SettingsRepository
import dvx.news.data.repositories.SettingsRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single<SettingsRepository> {
        SettingsRepositoryImpl(
            dataStore = get()
        )
    }

    single { DVXNewsClient() }
    single<ArticlesDataSource> { ArticlesRemoteDataSource(get()) }
    single<CategoriesDataSource> { CategoriesRemoteDataSource(get()) }
    single<ArticlesRepository> { AppwriteNewsRepository() }
    single<CategoriesRepository> {
        Appwrite.init(androidContext())
        AppwriteCategoriesRepository()
    }
}

val fakeDataModule = module {
    single<SettingsRepository> { SettingsRepositoryImpl(get()) }

    single<ArticlesRepository> { ArticlesFakeRepository() }
    single<CategoriesRepository> { CategoriesFakeRepository() }
}