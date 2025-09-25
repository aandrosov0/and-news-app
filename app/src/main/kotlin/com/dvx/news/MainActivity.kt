package com.dvx.news

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.datastore.preferences.preferencesDataStore
import com.dvx.news.ui.App
import com.dvx.news.ui.uiModule
import dvx.news.data.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.compose.KoinApplication

val Context.dataStore by preferencesDataStore("preferences")

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        installSplashScreen()
        actionBar?.hide()
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