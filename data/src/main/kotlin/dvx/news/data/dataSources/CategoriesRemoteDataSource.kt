package dvx.news.data.dataSources

import com.dvxnews.api.DVXNewsClient
import com.dvxnews.api.models.DVXCategory
import dvx.news.data.models.Category
import dvx.news.data.models.toCategory

class CategoriesRemoteDataSource(
    private val api: DVXNewsClient,
) : CategoriesDataSource {
    override suspend fun getAll(): List<Category> {
        return api.getCategories().map(DVXCategory::toCategory)
    }
}