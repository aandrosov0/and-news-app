package dvx.news.app

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.datastore.preferences.preferencesDataStore
import org.koin.compose.KoinApplication
import org.koin.core.KoinApplication

val Context.dataStore by preferencesDataStore("preferences")

fun KoinApplication.configureKoin(context: Context) {
    modules(getAndroidModule(context))
}

class MainActivity : ComponentActivity() {
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.hide()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        setContent {
            KoinApplication({ configureKoin(this@MainActivity) }) {
                App()
            }
        }
    }
}