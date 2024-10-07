package dvx.news.app

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import dvx.news.app.states.Settings
import dvx.news.app.states.getSavedSettings
import dvx.news.app.states.saveSettings
import dvx.news.app.themes.DVXTheme
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    private lateinit var settingsState: MutableState<Settings>

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.hide()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        runBlocking { settingsState = mutableStateOf(getSavedSettings()) }

        showContent()
    }

    override fun onSaveInstanceState(outState: Bundle) = runBlocking {
        saveSettings(settingsState.value)
        super.onSaveInstanceState(outState)
    }

    private fun showContent() = setContent {
        var settings by remember { settingsState }
        DVXTheme(theme = settings.theme) {
            App(
                settings = settings,
                onChangeSettings = { settings = it }
            )
        }
    }
}