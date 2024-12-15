package dvx.news.data.repositories

import dvx.news.data.models.Category

interface CategoriesRepository {
    suspend fun getAll(refresh: Boolean = false): List<Category>
}