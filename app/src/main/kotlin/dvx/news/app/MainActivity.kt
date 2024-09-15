package dvx.news.app

import android.graphics.drawable.ColorDrawable
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dvx.news.app.components.DVXBottomNavigation
import dvx.news.app.components.DVXTopAppBar
import dvx.news.app.components.DVXTopLogoAppBar
import dvx.news.app.screens.ArticleScreen
import dvx.news.app.screens.CategoryScreen
import dvx.news.app.screens.HomeScreen
import dvx.news.app.screens.MainScreen
import dvx.news.app.screens.NewsScreen
import dvx.news.app.screens.OverviewScreen
import dvx.news.app.screens.SettingsScreen
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
fun App(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController()
) {
    var currentScreen: Screen by remember { mutableStateOf(Screen.Main) }

    Scaffold(
        modifier = modifier,
        topBar = {
            when (currentScreen) {
                Screen.Main -> {}
                Screen.Category -> DVXTopAppBar(
                    title = "Lifestyle",
                    destination = "Mehr",
                )
                Screen.Article -> DVXTopAppBar(
                    title = "",
                    destination = "Zurück",
                    activeExport = true
                )
                Screen.Home -> DVXTopLogoAppBar()
                else -> DVXTopAppBar(
                    title = "",
                    destination = "Mehr"
                )
            }
        },
        bottomBar = {
            if (currentScreen != Screen.Main) {
                DVXBottomNavigation()
            }
        },
        containerColor = MaterialTheme.colorScheme.surfaceContainer
    ) { paddingValues ->
        NavHost(
            navController = navHostController,
            startDestination = Screen.Main
        ) {
            composable<Screen.Main> { backStackEntry ->
                currentScreen = backStackEntry.toRoute<Screen.Main>()
                MainScreen(
                    onChangeScreen = { navHostController.navigate(route = it) },
                    modifier.padding(paddingValues)
                )
            }
            composable<Screen.Home> { backStackEntry ->
                currentScreen = backStackEntry.toRoute<Screen.Home>()
                HomeScreen(modifier = Modifier.padding(paddingValues))
            }
            composable<Screen.News> { backStackEntry ->
                currentScreen = backStackEntry.toRoute<Screen.News>()
                NewsScreen(modifier = Modifier.padding(paddingValues))
            }
            composable<Screen.Category> { backStackEntry ->
                currentScreen = backStackEntry.toRoute<Screen.Category>()
                CategoryScreen(modifier = Modifier.padding(paddingValues))
            }
            composable<Screen.Article> { backStackEntry ->
                currentScreen = backStackEntry.toRoute<Screen.Article>()
                ArticleScreen(modifier = Modifier.padding(paddingValues))
            }
            composable<Screen.Overview> { backStackEntry ->
                currentScreen = backStackEntry.toRoute<Screen.Overview>()
                OverviewScreen(modifier = Modifier.padding(paddingValues))
            }
            composable<Screen.Settings> { backStackEntry ->
                currentScreen = backStackEntry.toRoute<Screen.Overview>()
                SettingsScreen(modifier = Modifier.padding(paddingValues))
            }
        }
    }
}

@Preview
@Composable
private fun AppPreview() {
    App()
}