package dvx.news.data.models

import com.dvxnews.api.models.DVXCategory

data class Category(
    val id: Long,
    val name: String,
)

fun DVXCategory.toCategory() = Category(
    id = id,
    name = name
)
