package dvx.news.data.dataSources

import com.dvxnews.api.DVXNewsClient
import com.dvxnews.api.models.DVXArticle
import com.dvxnews.api.models.DVXTextType
import dvx.news.data.models.ArticleContent
import dvx.news.data.models.ArticleContentElement
import dvx.news.data.models.toArticle
import dvx.news.data.models.toArticleContentImage
import dvx.news.data.models.toArticleContentText

class ArticlesRemoteDataSource(private val api: DVXNewsClient) : ArticlesDataSource {
    override suspend fun getRecent() = api
        .getArticles()
        .recent
        .map(DVXArticle::toArticle)

    override suspend fun getRandom() = api
        .getArticles()
        .random
        .map(DVXArticle::toArticle)

    override suspend fun getArticle(id: Long): ArticleContent {
        val article = api.getArticle(id)
        val images = article.images
        val text = article.text

        var currentImageIndex = 0
        val elements = buildList<ArticleContentElement> {
            text.forEach { text ->
                add(text.toArticleContentText())
                when (text.type) {
                    DVXTextType.SUBHEADING -> {
                        currentImageIndex++
                        images.getOrNull(currentImageIndex)?.let { add(it.toArticleContentImage()) }
                    }
                    DVXTextType.HEADLINE -> images.getOrNull(currentImageIndex)?.let { add(it.toArticleContentImage()) }
                    else -> {}
                }
            }
        }

        return ArticleContent(
            id = id,
            elements = elements
        )
    }

    override suspend fun getByCategory(categoryId: Long) = api
        .getArticles(categoryId = categoryId)
        .recent
        .map(DVXArticle::toArticle)
}