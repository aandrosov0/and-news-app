package com.dvx.news.ui.screens.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.dvx.news.ui.screens.NewsScreen
import com.dvx.news.ui.states.NewsTab
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