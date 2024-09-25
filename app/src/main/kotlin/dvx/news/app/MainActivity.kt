package dvx.news.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dvx.news.app.components.DVXBottomNavigation
import dvx.news.app.components.DVXTopAppBar
import dvx.news.app.components.DVXTopLogoAppBar
import dvx.news.app.screens.ArticleScreen
import dvx.news.app.screens.CategoryScreen
import dvx.news.app.screens.HomeScreen
import dvx.news.app.screens.IncludesScreen
import dvx.news.app.screens.MainScreen
import dvx.news.app.screens.NewsScreen
import dvx.news.app.screens.OverviewScreen
import dvx.news.app.screens.SettingsScreen
import dvx.news.app.states.Destination
import dvx.news.app.states.localizedName
import dvx.news.app.themes.DVXTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.hide()
        enableEdgeToEdge()

        setContent {
            DVXTheme {
                App()
            }
        }
    }
}

@Composable
fun AppTopBar(
    destination: Destination,
    onNavigateUp: () -> Unit,
) {
    val title = when (destination) {
        Destination.Article, Destination.News -> ""
        else -> destination.localizedName
    }

    val destinationText = when (destination) {
        Destination.Article -> "Zurück"
        else -> Destination.Menu.localizedName
    }

    when (destination) {
        Destination.Main -> {}
        Destination.Home -> DVXTopLogoAppBar()
        else -> DVXTopAppBar(
            title = title,
            destination = destinationText,
            onNavigateUp = onNavigateUp,
            activeExport = destination == Destination.Article
        )
    }
}

@Composable
fun AppBottomBar(
    destination: Destination,
    onDestinationChange: (Destination) -> Unit
) {
    when (destination) {
        Destination.Main -> {}
        else -> DVXBottomNavigation(
            current = destination,
            onDestinationSelect = { onDestinationChange(it) }
        )
    }
}

fun NavGraphBuilder.initializeAppNavigationGraph(
    navHostController: NavHostController,
    onDestinationChange: (Destination) -> Unit,
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
        SettingsScreen(modifier = modifier)
    }
    composable<Destination.Includes> {
        onDestinationChange(Destination.Includes)
        IncludesScreen(modifier = modifier)
    }
}

@Composable
fun App(
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
                onDestinationChange = { destination = it }
            )
        }
    }
}

@Preview
@Composable
private fun AppPreview() = DVXTheme {
    App()
}