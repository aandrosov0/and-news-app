package dvx.news.app

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dvx.news.data.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.compose.KoinApplication

class MainActivity : ComponentActivity() {
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            handleUncaughtException(throwable)
        }

        actionBar?.hide()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        setContent {
            KoinApplication({
                androidContext(this@MainActivity)
                modules(dataModule, appModule)
            }) {
                App()
            }
        }
    }

    private fun handleUncaughtException(throwable: Throwable) {
        throwable.printStackTrace(System.err)
        Toast
            .makeText(this, "Error has occurred", Toast.LENGTH_SHORT)
            .show()
    }
}