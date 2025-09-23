package dvx.news.data.appwrite.models

import com.google.gson.annotations.SerializedName

internal data class AppwriteCategoryModel(
    @SerializedName("\$id") val id: String,
    val name: String,
    val iconId: String
)
