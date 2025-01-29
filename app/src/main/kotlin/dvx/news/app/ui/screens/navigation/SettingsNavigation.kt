package dvx.news.app.ui.screens.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dvx.news.app.ui.screens.SettingsScreen
import kotlinx.serialization.Serializable

@Serializable
data object Settings

internal fun NavGraphBuilder.settingsDestination(navController: NavController) {
    composable<Settings> { SettingsScreen(navController = navController) }
}