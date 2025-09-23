package dvx.news.data.repositories

import dvx.news.data.core.LayerExceptionConverter
import dvx.news.data.core.LayerExceptionConverterImpl
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

    private val layerExceptionConverter: LayerExceptionConverter = LayerExceptionConverterImpl

    override suspend fun getRecent(refresh: Boolean) = withContext(dispatcher) {
        try {
            if (refresh || recentArticles.isEmpty()) {
                recentArticlesMutex.withLock {
                    recentArticles = articlesDataSource.getRecent()
                }
            }
            recentArticles
        } catch (exception: Exception) {
            throw layerExceptionConverter.convert(exception)
        }
    }

    override suspend fun getRandom(refresh: Boolean) = withContext(dispatcher) {
        try {
            if (refresh || randomArticles.isEmpty()) {
                randomArticlesMutex.withLock {
                    randomArticles = articlesDataSource.getRandom()
                }
            }
            randomArticles
        } catch (exception: Exception) {
            throw layerExceptionConverter.convert(exception)
        }
    }

    override suspend fun getArticle(articleId: String) = withContext(dispatcher) {
        try {
            articlesDataSource.getArticle(articleId)
        } catch (exception: Exception) {
            throw layerExceptionConverter.convert(exception)
        }
    }

    override suspend fun getByCategory(categoryId: String) = withContext(dispatcher) {
        try {
            articlesDataSource.getByCategory(categoryId)
        } catch (exception: Exception) {
            throw layerExceptionConverter.convert(exception)
        }
    }
}