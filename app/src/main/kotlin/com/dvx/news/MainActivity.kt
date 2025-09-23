package com.dvx.news

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.datastore.preferences.preferencesDataStore
import com.dvx.news.ui.App
import com.dvx.news.ui.uiModule
import dvx.news.data.dataModule
import dvx.news.data.fakeDataModule
import org.koin.android.ext.koin.androidContext
import org.koin.compose.KoinApplication

val Context.dataStore by preferencesDataStore("preferences")

class MainActivity : AppCompatActivity() {
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        installSplashScreen()
        adaptCurrentTheme(isLight = true)
        actionBar?.hide()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        super.onCreate(savedInstanceState)

        setContent {
            KoinApplication(application = {
                androidContext(this@MainActivity)
                modules(dataModule, uiModule)
            }) {
                App()
            }
        }
    }
}

fun Activity.adaptCurrentTheme(isLight: Boolean) {
    WindowCompat.getInsetsController(window, window.decorView)
        .isAppearanceLightStatusBars = isLight
}