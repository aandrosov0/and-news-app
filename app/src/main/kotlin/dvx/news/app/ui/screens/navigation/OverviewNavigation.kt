package dvx.news.app.ui.screens.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dvx.news.app.ui.screens.OverviewScreen
import kotlinx.serialization.Serializable

@Serializable
data object Overview

internal fun NavGraphBuilder.overviewDestination(navController: NavController) {
    composable<Overview> { OverviewScreen(onNavigateScreen = navController::navigate) }
}