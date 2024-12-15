package dvx.news.data.repositories

import dvx.news.data.dataSources.CategoriesDataSource
import dvx.news.data.models.Category
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

class CategoriesRepositoryImpl(
    private val categoriesDataSource: CategoriesDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : CategoriesRepository {

    private val categoriesMutex = Mutex()
    private var categories =  listOf<Category>()

    override suspend fun getAll(refresh: Boolean) = withContext(dispatcher) {
        if (refresh || categories.isEmpty()) {
            categoriesMutex.withLock {
                categories = categoriesDataSource.getAll()
            }
        }
        categories
    }
}