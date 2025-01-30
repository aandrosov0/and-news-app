package dvx.news.data.repositories

import dvx.news.data.models.Category

class CategoriesFakeRepository : CategoriesRepository {
    private val categories = listOf(
        Category(
            id = 30,
            name = "News"
        ),
        Category(
            id = 55,
            name = "Cars"
        ),
        Category(
            id = 52,
            name = "Soccer"
        ),
        Category(
            id = 18,
            name = "Lifestyle"
        ),
        Category(
            id = 196,
            name = "Games"
        )
    )

    override suspend fun getAll(refresh: Boolean): List<Category> {
        return categories
    }
}