package dvx.news.app

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring.StiffnessLow
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dvx.news.app.components.DVXBottomNavigation
import dvx.news.app.components.DVXTopAppBar
import dvx.news.app.screens.ArticleScreen
import dvx.news.app.screens.CategoryScreen
import dvx.news.app.screens.HomeScreen
import dvx.news.app.screens.IncludesScreen
import dvx.news.app.screens.NewsScreen
import dvx.news.app.screens.OverviewScreen
import dvx.news.app.screens.SettingsScreen
import dvx.news.app.states.Destination
import dvx.news.app.states.TopAppBarDefaults
import dvx.news.app.states.topAppBarParameters
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
            topBar = {
                AnimatedVisibility(
                    destination.topAppBarParameters != null,
                    exit = fadeOut() + shrinkOut(shrinkTowards = Alignment.Center),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    DVXTopAppBar(
                        navController = navController,
                        topAppBarParameters = destination.topAppBarParameters ?: TopAppBarDefaults.topAppBarParameters,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            bottomBar = { DVXBottomNavigation(navController) },
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = Destination.Home,
                enterTransition = { fadeIn(tween(1000)) },
                exitTransition = { fadeOut(tween(1000)) },
                modifier = Modifier.padding(paddingValues)
            ) {
                composable<Destination.Home> {
                    destination = Destination.Home
                    HomeScreen(navController = navController)
                }
                composable<Destination.Sport> {
                    destination = Destination.Sport
                    CategoryScreen(
                        navController = navController,
                    )
                }
                composable<Destination.Lifestyle> {
                    destination = Destination.Lifestyle
                    CategoryScreen(
                        navController = navController,
                    )
                }
                composable<Destination.Entertainment> {
                    destination = Destination.Entertainment
                    CategoryScreen(
                        navController = navController,
                    )
                }
                composable<Destination.Menu>(
                    enterTransition = {
                        slideIntoContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Left,
                            animationSpec = spring(stiffness = StiffnessLow)
                        )
                    },
                    exitTransition = {
                        slideOutOfContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Right,
                            animationSpec = spring(stiffness = StiffnessLow)
                        )
                    }
                ) {
                    destination = Destination.Menu
                    OverviewScreen(navController = navController)
                }
                composable<Destination.Article> {
                    destination = Destination.Article
                    ArticleScreen()
                }
                composable<Destination.News> {
                    destination = it.toRoute<Destination.News>()
                    NewsScreen(
                        initialTab = it.toRoute<Destination.News>().initialTab,
                        navController = navController
                    )
                }
                composable<Destination.Settings> {
                    destination = it.toRoute<Destination.Settings>()
                    SettingsScreen(
                        settingsViewModel = settingsViewModel
                    )
                }
                composable<Destination.Includes> {
                    destination = Destination.Includes
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