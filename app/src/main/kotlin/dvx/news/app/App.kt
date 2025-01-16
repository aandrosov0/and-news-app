package dvx.news.app

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.Spring.StiffnessLow
import androidx.compose.animation.core.spring
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dvx.news.app.states.ArticleScreen
import dvx.news.app.states.CategoryScreen
import dvx.news.app.states.CategoryUiState
import dvx.news.app.states.Destination
import dvx.news.app.states.HomeScreen
import dvx.news.app.states.IncludesScreen
import dvx.news.app.states.MainUiState
import dvx.news.app.states.MenuScreen
import dvx.news.app.states.NewsScreen
import dvx.news.app.states.SettingsScreen
import dvx.news.app.themes.DVXTheme
import dvx.news.app.ui.components.DVXBottomNavigation
import dvx.news.app.ui.screens.ArticleScreen
import dvx.news.app.ui.screens.CategoryScreen
import dvx.news.app.ui.screens.HomeScreen
import dvx.news.app.ui.screens.IncludesScreen
import dvx.news.app.ui.screens.NewsScreen
import dvx.news.app.ui.screens.OverviewScreen
import dvx.news.app.ui.screens.SettingsScreen
import dvx.news.app.viewModels.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun App(
    onCloseSplashScreen: () -> Unit,
    mainViewModel: MainViewModel = koinViewModel()
) {
    LaunchedEffect(Unit) { mainViewModel.load() }
    val uiState by mainViewModel.uiState.collectAsState()

    LaunchedEffect(uiState.isLoading) {
        if (!uiState.isLoading) {
            onCloseSplashScreen()
        }
    }

    DVXTheme(theme = uiState.settings.theme) {
        AppContent(mainUiState = uiState)
    }
}

@Composable
private fun AppContent(
    mainUiState: MainUiState,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    var destination by remember { mutableStateOf<Destination>(HomeScreen) }
    val categories = mainUiState.categories
    val destinations = buildList {
        add(HomeScreen)
        categories.getOrNull(0)?.let { add(CategoryScreen(it.id, it.name)) }
        categories.getOrNull(1)?.let { add(CategoryScreen(it.id, it.name)) }
        categories.getOrNull(2)?.let { add(CategoryScreen(it.id, it.name)) }
        add(MenuScreen)
    }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            DVXBottomNavigation(
                destination = destination,
                destinations = destinations,
                onDestinationChange = {
                    if (destination != it) {
                        navController.navigate(it) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    ) { paddings ->
        AppNavigation(
            mainUiState = mainUiState,
            modifier = Modifier.padding(paddings),
            onDestinationChange = { destination = it },
            navController = navController
        )
    }
}

@Composable
private fun AppNavigation(
    mainUiState: MainUiState,
    onDestinationChange: (Destination) -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = HomeScreen,
        modifier = modifier,
    ) {
        composable<HomeScreen> {
            onDestinationChange(HomeScreen)
            HomeScreen(navController = navController)
        }
        composable<CategoryScreen> { backStackEntry ->
            val category = backStackEntry.toRoute<CategoryScreen>()
            onDestinationChange(category)
            CategoryScreen(
                category = CategoryUiState(category.id, category.name),
                navController = navController
            )
        }
        composable<MenuScreen>(
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
            onDestinationChange(MenuScreen)
            OverviewScreen(
                categories = mainUiState.categories,
                navController = navController
            )
        }
        composable<ArticleScreen> { backStackEntry ->
            val article = backStackEntry.toRoute<ArticleScreen>()
            onDestinationChange(article)
            ArticleScreen(
                id = article.id,
                navController = navController
            )
        }
        composable<NewsScreen> { backStackEntry ->
            val news = backStackEntry.toRoute<NewsScreen>()
            onDestinationChange(news)
            NewsScreen(
                navController = navController,
                initialTab = news.initialTab
            )
        }
        composable<SettingsScreen> {
            onDestinationChange(SettingsScreen)
            SettingsScreen(
                settings = mainUiState.settings,
                onUpdateSettings = mainUiState.onSettingsChange,
                navController = navController
            )
        }
        composable<IncludesScreen> {
            onDestinationChange(IncludesScreen)
            IncludesScreen(navController = navController)
        }
    }
}