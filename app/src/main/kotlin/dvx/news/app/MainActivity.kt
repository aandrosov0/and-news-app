package dvx.news.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dvx.news.app.components.DVXBottomNavigation
import dvx.news.app.components.DVXTopAppBar
import dvx.news.app.components.DVXTopLogoAppBar
import dvx.news.app.screens.ArticleScreen
import dvx.news.app.screens.CategoryScreen
import dvx.news.app.screens.HomeScreen
import dvx.news.app.screens.NewsScreen
import dvx.news.app.states.Screen
import dvx.news.app.themes.DVXTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.hide()

        setContent {
            DVXTheme {
                App()
            }
        }
    }
}

@Composable
fun App(modifier: Modifier = Modifier) {
    val currentScreen by remember { mutableStateOf(Screen.ARTICLE) }

    Scaffold(
        modifier = modifier,
        topBar = {
            when (currentScreen) {
                Screen.HOME -> DVXTopLogoAppBar()
                Screen.CATEGORY -> DVXTopAppBar(
                    title = "Lifestyle",
                    destination = "Mehr",
                )
                Screen.ARTICLE -> DVXTopAppBar(
                    title = "",
                    destination = "Zurück",
                    activeExport = true
                )
                else -> DVXTopAppBar(
                    title = "",
                    destination = "Mehr"
                )
            }
        },
        bottomBar = { DVXBottomNavigation() },
        containerColor = MaterialTheme.colorScheme.surfaceContainer
    ) { paddingValues ->
        when (currentScreen) {
            Screen.HOME -> HomeScreen(modifier = Modifier.padding(paddingValues))
            Screen.NEWS -> NewsScreen(modifier = Modifier.padding(paddingValues))
            Screen.CATEGORY -> CategoryScreen(modifier = Modifier.padding(paddingValues))
            Screen.ARTICLE -> ArticleScreen(modifier = Modifier.padding(paddingValues))
        }
    }
}

@Preview
@Composable
private fun AppPreview() {
    App()
}