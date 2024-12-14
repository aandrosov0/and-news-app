package dvx.news.data.dataSources

import com.dvxnews.api.DVXNewsClient
import com.dvxnews.api.exceptions.DVXNewsException
import com.dvxnews.api.models.DVXArticle
import dvx.news.data.exceptions.DataLayerException
import dvx.news.data.models.Article
import dvx.news.data.models.toArticle

class ArticlesRemoteDataSource(
    private val api: DVXNewsClient
) : ArticlesDataSource {
    override suspend fun getRecent(): List<Article> {
        try {
            return api
                .getArticles()
                .recent
                .map(DVXArticle::toArticle)
        } catch (exception: DVXNewsException) {
            throw DataLayerException(exception)
        }
    }

    override suspend fun getRandom(): List<Article> {
        try {
            return api
                .getArticles()
                .random
                .map(DVXArticle::toArticle)
        } catch (exception: DVXNewsException) {
            throw DataLayerException(exception)
        }
    }
}