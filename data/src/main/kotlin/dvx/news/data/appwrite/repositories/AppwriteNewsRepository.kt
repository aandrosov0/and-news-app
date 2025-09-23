package dvx.news.data.appwrite.repositories

import dvx.news.data.ArticleRenderer
import dvx.news.data.appwrite.Appwrite
import dvx.news.data.appwrite.models.AppwriteNewsModel
import dvx.news.data.models.Article
import dvx.news.data.models.ArticleContent
import dvx.news.data.repositories.ArticlesRepository
import io.appwrite.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private const val NEWS_TABLE_ID = "news"

class AppwriteNewsRepository : ArticlesRepository {
    val tablesDB = Appwrite.tablesDB

    override suspend fun getRecent(refresh: Boolean) = withContext(Dispatchers.IO) {
        val (_, rows) = tablesDB.listRows(
            databaseId = Appwrite.DATABASE_ID,
            tableId = NEWS_TABLE_ID,
            queries = listOf(Query.orderAsc("\$createdAt")),
            nestedType = AppwriteNewsModel::class.java
        )

        rows.map { it.data.toArticle() }
    }

    override suspend fun getRandom(refresh: Boolean) = withContext(Dispatchers.IO) {
        val (_, rows) = tablesDB.listRows(
            databaseId = Appwrite.DATABASE_ID,
            tableId = NEWS_TABLE_ID,
            queries = listOf(Query.orderAsc("\$createdAt")),
            nestedType = AppwriteNewsModel::class.java
        )

        rows.map { it.data.toArticle() }.shuffled()
    }

    override suspend fun getArticle(articleId: String) = withContext(Dispatchers.IO) {
        val row = tablesDB.getRow(
            rowId = articleId,
            databaseId = Appwrite.DATABASE_ID,
            tableId = NEWS_TABLE_ID,
            queries = listOf(Query.select(listOf("text")))
        )

        val text = row.data["text"].toString()
        val content = ArticleRenderer().renderText(text)
        ArticleContent(articleId, content)
    }

    override suspend fun getByCategory(categoryId: String) = withContext(Dispatchers.IO) {
        val (_, rows) = tablesDB.listRows(
            databaseId = Appwrite.DATABASE_ID,
            tableId = NEWS_TABLE_ID,
            queries = listOf(Query.equal("category", categoryId)),
            nestedType = AppwriteNewsModel::class.java
        )

        rows.map { it.data.toArticle() }
    }
}

internal fun AppwriteNewsModel.toArticle() = Article(
    id = id,
    categoryId = categoryId,
    headline = headline,
    subheadline = subheadline,
    categoryName = "",
    slug = "",
    leadParagraph = "",
    time = createdAt.substringBefore("T"),
    imageUrl = imageUrl,
    squareImageUrl = imageUrl,
)