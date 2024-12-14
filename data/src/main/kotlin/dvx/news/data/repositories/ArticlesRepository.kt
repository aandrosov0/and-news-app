package dvx.news.data.repositories

import dvx.news.data.models.Article

interface ArticlesRepository {
    suspend fun getRecent(refresh: Boolean = false): List<Article>
    suspend fun getRandom(refresh: Boolean = false): List<Article>
}