package dvx.news.data.dataSources

import dvx.news.data.models.Article

interface ArticlesDataSource {
    suspend fun getRecent(): List<Article>
    suspend fun getRandom(): List<Article>
    suspend fun getByCategory(categoryId: Long): List<Article>
}