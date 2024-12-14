package dvx.news.data

import com.dvxnews.api.DVXNewsClient
import dvx.news.data.dataSources.ArticlesDataSource
import dvx.news.data.dataSources.ArticlesRemoteDataSource
import dvx.news.data.repositories.ArticlesRepository
import dvx.news.data.repositories.ArticlesRepositoryImpl
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
    single<ArticlesRepository> { ArticlesRepositoryImpl(get()) }
}