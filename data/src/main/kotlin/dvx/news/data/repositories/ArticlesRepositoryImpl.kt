package dvx.news.data.repositories

import dvx.news.data.dataSources.ArticlesDataSource
import dvx.news.data.models.Article
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

class ArticlesRepositoryImpl(
    private val articlesDataSource: ArticlesDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ArticlesRepository {
    private val recentArticlesMutex = Mutex()
    private var recentArticles = emptyList<Article>()

    private val randomArticlesMutex = Mutex()
    private var randomArticles = emptyList<Article>()

    override suspend fun getRecent(refresh: Boolean) = withContext(dispatcher) {
        if (refresh || recentArticles.isEmpty()) {
            recentArticlesMutex.withLock {
                recentArticles = articlesDataSource.getRecent()
            }
        }
        recentArticles
    }

    override suspend fun getRandom(refresh: Boolean) = withContext(dispatcher) {
        if (refresh || randomArticles.isEmpty()) {
            randomArticlesMutex.withLock {
                randomArticles = articlesDataSource.getRandom()
            }
        }
        randomArticles
    }
}