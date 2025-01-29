package dvx.news.app.ui.screens.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dvx.news.app.ui.screens.IncludesScreen
import kotlinx.serialization.Serializable

@Serializable
data object Includes

internal fun NavGraphBuilder.includesDestination(navController: NavController) {
    composable<Includes> { IncludesScreen(navController = navController) }
}