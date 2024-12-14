package dvx.news.data.repositories

import dvx.news.data.dataSources.ArticlesDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class ArticlesRepositoryImpl(
    private val newsSource: ArticlesDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ArticlesRepository {
    override suspend fun getRecent() = with(dispatcher) {
        newsSource.getRecent()
    }

    override suspend fun getRandom() = with(dispatcher) {
        newsSource.getRandom()
    }
}