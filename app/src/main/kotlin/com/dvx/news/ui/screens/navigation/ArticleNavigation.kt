package com.dvx.news.ui.screens.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.dvx.news.ui.screens.ArticleScreen
import kotlinx.serialization.Serializable

@Serializable
data class Article(val id: String)

internal fun NavGraphBuilder.articleDestination(navController: NavController) {
    composable<Article> { backStackEntry ->
        val route = backStackEntry.toRoute<Article>()
        ArticleScreen(
            id = route.id,
            onNavigateUp = navController::navigateUp,
            onNavigateScreen = navController::navigate
        )
    }
}