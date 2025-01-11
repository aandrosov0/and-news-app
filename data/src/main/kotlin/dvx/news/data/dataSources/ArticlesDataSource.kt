package dvx.news.data.dataSources

import dvx.news.data.models.Article
import dvx.news.data.models.ArticleContent

interface ArticlesDataSource {
    suspend fun getRecent(): List<Article>
    suspend fun getRandom(): List<Article>
    suspend fun getArticle(id: Long): ArticleContent
    suspend fun getByCategory(categoryId: Long): List<Article>
}