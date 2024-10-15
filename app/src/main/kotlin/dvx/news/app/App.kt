package dvx.news.app

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dvx.news.app.components.AppBottomBar
import dvx.news.app.screens.ArticleScreen
import dvx.news.app.screens.CategoryScreen
import dvx.news.app.screens.HomeScreen
import dvx.news.app.screens.IncludesScreen
import dvx.news.app.screens.NewsScreen
import dvx.news.app.screens.OverviewScreen
import dvx.news.app.screens.SettingsScreen
import dvx.news.app.states.Destination
import dvx.news.app.themes.DVXTheme
import dvx.news.app.viewModels.SettingsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun App(
    modifier: Modifier = Modifier,
    settingsViewModel: SettingsViewModel = koinViewModel(),
    navController: NavHostController = rememberNavController()
) {
    val settings by settingsViewModel.state.collectAsState()
    LaunchedEffect(Unit) { settingsViewModel.getSaved() }

    DVXTheme(theme = settings.theme) {
        var destination: Destination by remember { mutableStateOf(Destination.Home) }

        Scaffold(
            modifier = modifier,
            bottomBar = {
                AppBottomBar(
                    destination,
                    onDestinationChange = {
                        navController.navigate(it)
                        destination = it
                    }
                )
            },
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = destination,
                modifier = Modifier.padding(paddingValues)
            ) {
                composable<Destination.Home> {
                    HomeScreen(navController = navController)
                }
                composable<Destination.Sport> {
                    CategoryScreen(
                        navController = navController,
                        category = stringResource(R.string.sport),
                    )
                }
                composable<Destination.Lifestyle> {
                    CategoryScreen(
                        navController = navController,
                        category = stringResource(R.string.lifestyle),
                    )
                }
                composable<Destination.Entertainment> {
                    CategoryScreen(
                        navController = navController,
                        category = stringResource(R.string.entertainment),
                    )
                }
                composable<Destination.Menu> {
                    OverviewScreen(navController = navController)
                }
                composable<Destination.Article> {
                    ArticleScreen(navController = navController)
                }
                composable<Destination.News> {
                    NewsScreen(navController = navController)
                }
                composable<Destination.Settings> {
                    SettingsScreen(
                        navController = navController,
                        settingsViewModel = settingsViewModel
                    )
                }
                composable<Destination.Includes> {
                    IncludesScreen(navController = navController)
                }
            }
        }
    }

}

@Preview
@Composable
private fun AppPreview() = DVXTheme {
    App()
}