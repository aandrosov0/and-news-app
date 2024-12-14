package dvx.news.data.repositories

import dvx.news.data.models.Article

interface ArticlesRepository {
    suspend fun getRecent(): List<Article>
    suspend fun getRandom(): List<Article>
}