package dvx.news.data.repositories

import dvx.news.data.dataSources.ArticlesDataSource
import dvx.news.data.models.Article
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class ArticlesRepositoryImpl(
    private val newsSource: ArticlesDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ArticlesRepository {
    private val recentArticlesMutex = Mutex()
    private var recentArticles = emptyList<Article>()

    override suspend fun getRecent(refresh: Boolean) = with(dispatcher) {
        if (refresh || recentArticles.isEmpty()) {
            recentArticlesMutex.withLock {
                recentArticles = newsSource.getRecent()
            }
        }
        recentArticles
    }

    override suspend fun getRandom(refresh: Boolean) = with(dispatcher) {
        newsSource.getRandom()
    }
}