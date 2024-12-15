package dvx.news.data

import com.dvxnews.api.DVXNewsClient
import dvx.news.data.dataSources.ArticlesDataSource
import dvx.news.data.dataSources.ArticlesOfflineDataSource
import dvx.news.data.dataSources.ArticlesRemoteDataSource
import dvx.news.data.dataSources.CategoriesDataSource
import dvx.news.data.dataSources.CategoriesOfflineDataSource
import dvx.news.data.dataSources.CategoriesRemoteDataSource
import dvx.news.data.repositories.ArticlesRepository
import dvx.news.data.repositories.ArticlesRepositoryImpl
import dvx.news.data.repositories.CategoriesRepository
import dvx.news.data.repositories.CategoriesRepositoryImpl
import dvx.news.data.repositories.SettingsRepository
import dvx.news.data.repositories.SettingsRepositoryImpl
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
    single<ArticlesRepository> { ArticlesRepositoryImpl(get()) }
    single<CategoriesRepository> { CategoriesRepositoryImpl(get()) }
}

val dataOfflineModule = module {
    single<ArticlesDataSource> { ArticlesOfflineDataSource() }
    single<CategoriesDataSource> { CategoriesOfflineDataSource() }
    single<ArticlesRepository> { ArticlesRepositoryImpl(get()) }
    single<CategoriesRepository> { CategoriesRepositoryImpl(get()) }
}