package dvx.news.data.dataSources

import dvx.news.data.models.Category
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

class CategoriesOfflineDataSource : CategoriesDataSource {
    override suspend fun getAll(): List<Category> {
        delay(3.seconds)
         return listOf(
            Category(id = 1, name = "Electronics"),
            Category(id = 2, name = "Books"),
            Category(id = 3, name = "Clothing"),
            Category(id = 4, name = "Home & Kitchen"),
            Category(id = 5, name = "Sports & Outdoors"),
            Category(id = 6, name = "Toys & Games"),
            Category(id = 7, name = "Health & Personal Care"),
            Category(id = 8, name = "Automotive"),
            Category(id = 9, name = "Beauty"),
            Category(id = 10, name = "Grocery")
        )
    }
}