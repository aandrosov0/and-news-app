package dvx.news.data.repositories

import dvx.news.data.models.Article
import dvx.news.data.models.ArticleContent

interface ArticlesRepository {
    suspend fun getRecent(refresh: Boolean = false): List<Article>
    suspend fun getRandom(refresh: Boolean = false): List<Article>
    suspend fun getArticle(articleId: String): ArticleContent
    suspend fun getByCategory(categoryId: String): List<Article>
}