package dvx.news.data.appwrite.models

import com.google.gson.annotations.SerializedName

internal data class AppwriteNewsModel(
    @SerializedName("\$id") val id: String,
    @SerializedName("\$createdAt") val createdAt: String,
    @SerializedName("category") val categoryId: String,
    val headline: String,
    val subheadline: String,
    val imageUrl: String,
    val text: String
)
