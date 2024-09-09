package dvx.news.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dvx.news.app.components.DVXBottomNavigation
import dvx.news.app.components.DVXTopAppBar
import dvx.news.app.screens.HomeScreen
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
    Scaffold(
        modifier = modifier,
        topBar = {
            DVXTopAppBar(
                title = "Lifestyle",
                destination = "Mehr"
            )
        },
        bottomBar = { DVXBottomNavigation() },
    ) { paddingValues ->
        HomeScreen(modifier = Modifier.padding(paddingValues))
    }
}

@Preview
@Composable
private fun AppPreview() {
    App()
}