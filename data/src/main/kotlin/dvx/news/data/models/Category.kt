package dvx.news.data.models

import com.dvxnews.api.models.DVXCategory

data class Category(
    val id: String,
    val name: String,
    val iconId: String = "",
)

fun DVXCategory.toCategory() = Category(
    id = id.toString(),
    name = name
)
