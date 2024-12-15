package dvx.news.data.dataSources

import dvx.news.data.models.Category

interface CategoriesDataSource {
    suspend fun getAll(): List<Category>
}