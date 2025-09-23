package dvx.news.data.appwrite.repositories

import dvx.news.data.appwrite.Appwrite
import dvx.news.data.appwrite.models.AppwriteCategoryModel
import dvx.news.data.models.Category
import dvx.news.data.repositories.CategoriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val CATEGORIES_TABLE_ID = "categories"

class AppwriteCategoriesRepository : CategoriesRepository {
    private val tablesDB = Appwrite.tablesDB

    override suspend fun getAll(refresh: Boolean) = withContext(Dispatchers.IO) {
        val (_, rows) = tablesDB.listRows(
            databaseId = Appwrite.DATABASE_ID,
            tableId = CATEGORIES_TABLE_ID,
            nestedType = AppwriteCategoryModel::class.java
        )

        rows.map { it.data.toCategory() }
    }
}

internal fun AppwriteCategoryModel.toCategory() = Category(
    id = id,
    name = name,
    iconId = iconId
)