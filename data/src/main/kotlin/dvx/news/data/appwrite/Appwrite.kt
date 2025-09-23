/*
Create your own AppwriteConfig.kt in the current package and specify top-level constants:
private const val APPWRITE_PROJECT_ID = ""
private const val APPWRITE_PUBLIC_ENDPOINT = ""
 */
package dvx.news.data.appwrite

import android.content.Context
import io.appwrite.Client
import io.appwrite.services.TablesDB

internal object Appwrite {
    internal const val DATABASE_ID = "68d24c40003b04023f48"

    internal lateinit var client: Client
        private set

    internal lateinit var tablesDB: TablesDB
        private set

    fun init(context: Context) {
        client = Client(context)
            .setEndpoint(APPWRITE_PUBLIC_ENDPOINT)
            .setProject(APPWRITE_PROJECT_ID)

        tablesDB = TablesDB(client)
    }
}