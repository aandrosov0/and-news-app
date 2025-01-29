package dvx.news.app.ui.screens.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dvx.news.app.ui.screens.HomeScreen
import kotlinx.serialization.Serializable

@Serializable
data object Home

internal fun NavGraphBuilder.homeDestination(navController: NavController) {
    composable<Home> { HomeScreen(navController = navController) }
}