package dvx.news.data.dataSources

import com.dvxnews.api.DVXNewsClient
import com.dvxnews.api.models.DVXArticle
import dvx.news.data.core.LayerExceptionConverterImpl
import dvx.news.data.core.LayerExceptionConverter
import dvx.news.data.models.Article
import dvx.news.data.models.ArticleContent
import dvx.news.data.models.toArticle
import dvx.news.data.models.toArticleContent

class ArticlesRemoteDataSource(private val api: DVXNewsClient) : ArticlesDataSource {
    private val layerExceptionConverter: LayerExceptionConverter = LayerExceptionConverterImpl

    override suspend fun getRecent(): List<Article> {
        try {
            return api
                .getArticles()
                .recent
                .map(DVXArticle::toArticle)
        } catch (exception: Exception) {
            throw layerExceptionConverter.convert(exception)
        }
    }

    override suspend fun getRandom(): List<Article> {
        try {
            return api
                .getArticles()
                .random
                .map(DVXArticle::toArticle)
        } catch (exception: Exception) {
            throw layerExceptionConverter.convert(exception)
        }
    }

    override suspend fun getArticle(id: Long): ArticleContent {
        try {
            return api
                .getArticle(id)
                .toArticleContent()
        } catch (exception: Exception) {
            throw layerExceptionConverter.convert(exception)
        }
    }

    override suspend fun getByCategory(categoryId: Long): List<Article> {
        try {
            return api
                .getArticles(categoryId = categoryId)
                .recent
                .map(DVXArticle::toArticle)
        } catch (exception: Exception) {
            throw layerExceptionConverter.convert(exception)
        }
    }
}