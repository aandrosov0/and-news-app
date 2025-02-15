package dvx.news.app.ui.screens.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import dvx.news.app.ui.screens.NewsScreen
import dvx.news.app.ui.states.NewsTab
import kotlinx.serialization.Serializable

@Serializable
data class News(val tab: NewsTab)

internal fun NavGraphBuilder.newsDestination(navController: NavController) {
    composable<News> { backStackEntry ->
        val route = backStackEntry.toRoute<News>()
        NewsScreen(
            initialTab = route.tab,
            onNavigateUp = navController::navigateUp,
            onNavigateScreen = navController::navigate
        )
    }
}