package dvx.news.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dvx.news.app.components.AppBottomBar
import dvx.news.app.components.AppTopBar
import dvx.news.app.screens.ArticleScreen
import dvx.news.app.screens.CategoryScreen
import dvx.news.app.screens.HomeScreen
import dvx.news.app.screens.IncludesScreen
import dvx.news.app.screens.MainScreen
import dvx.news.app.screens.NewsScreen
import dvx.news.app.screens.OverviewScreen
import dvx.news.app.screens.SettingsScreen
import dvx.news.app.states.Destination
import dvx.news.app.themes.DVXTheme
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    private lateinit var settingsState: MutableState<Settings>

    override fun onCreate(savedInstanceState: Bundle?) = runBlocking {
        super.onCreate(savedInstanceState)
        actionBar?.hide()
        enableEdgeToEdge()

        settingsState = mutableStateOf(getSavedSettings())
        setContent {
            var settings by remember { settingsState }

            DVXTheme(theme = settings.theme) {
                App(
                    settings = settings,
                    onChangeSettings = { settings = it }
                )
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) = runBlocking {
        saveSettings(settingsState.value)
        super.onSaveInstanceState(outState)
    }
}

fun NavGraphBuilder.initializeAppNavigationGraph(
    navHostController: NavHostController,
    settings: Settings,
    onDestinationChange: (Destination) -> Unit,
    onChangeSettings: (Settings) -> Unit,
    modifier: Modifier = Modifier
) {
    composable<Destination.Main> {
        onDestinationChange(Destination.Main)
        MainScreen(
            onDestinationChange = { navHostController.navigate(route = it) },
            modifier = modifier
        )
    }
    composable<Destination.Home> {
        onDestinationChange(Destination.Home)
        HomeScreen(modifier = modifier)
    }
    composable<Destination.News> {
        onDestinationChange(Destination.News)
        NewsScreen(modifier = modifier)
    }
    composable<Destination.Category> {
        onDestinationChange(Destination.Category)
        CategoryScreen(modifier = modifier)
    }
    composable<Destination.Article> {
        onDestinationChange(Destination.Article)
        ArticleScreen(modifier = modifier)
    }
    composable<Destination.Overview> {
        onDestinationChange(Destination.Overview)
        OverviewScreen(modifier = modifier)
    }
    composable<Destination.Settings> {
        onDestinationChange(Destination.Settings)
        SettingsScreen(
            modifier = modifier,
            settings = settings,
            onChangeSettings = onChangeSettings,
        )
    }
    composable<Destination.Includes> {
        onDestinationChange(Destination.Includes)
        IncludesScreen(modifier = modifier)
    }
}

@Composable
fun App(
    settings: Settings,
    onChangeSettings: (Settings) -> Unit,
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController()
) {
    var destination: Destination by remember { mutableStateOf(Destination.Main) }
    Scaffold(
        modifier = modifier,
        topBar = {
            AppTopBar(
                destination = destination,
                onNavigateUp = navHostController::navigateUp
            )
        },
        bottomBar = {
            AppBottomBar(
                destination,
                onDestinationChange = { destination = it }
            )
        },
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    ) { paddingValues ->
        NavHost(
            navController = navHostController,
            startDestination = Destination.Main
        ) {
            initializeAppNavigationGraph(
                navHostController = navHostController,
                modifier = Modifier.padding(paddingValues),
                onDestinationChange = { destination = it },
                settings = settings,
                onChangeSettings = onChangeSettings
            )
        }
    }
}

@Preview
@Composable
private fun AppPreview() = DVXTheme {
    App(
        settings = Settings(),
        onChangeSettings = {}
    )
}