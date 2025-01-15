package dvx.news.data.dataSources

import com.dvxnews.api.DVXNewsClient
import com.dvxnews.api.models.DVXCategory
import dvx.news.data.core.LayerExceptionConverter
import dvx.news.data.core.LayerExceptionConverterImpl
import dvx.news.data.models.Category
import dvx.news.data.models.toCategory

class CategoriesRemoteDataSource(
    private val api: DVXNewsClient,
) : CategoriesDataSource {
    private val layerExceptionConverter: LayerExceptionConverter = LayerExceptionConverterImpl

    override suspend fun getAll(): List<Category> {
        try {
            return api.getCategories().map(DVXCategory::toCategory)
        } catch (exception: Exception) {
            throw layerExceptionConverter.convert(exception)
        }
    }
}