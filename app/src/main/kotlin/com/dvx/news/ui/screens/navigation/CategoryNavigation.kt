package com.dvx.news.ui.screens.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.dvx.news.ui.screens.CategoryScreen
import kotlinx.serialization.Serializable

@Serializable
data class Category(val id: String, val name: String)

internal fun NavGraphBuilder.categoryDestination(navController: NavController) {
    composable<Category> { backStackEntry ->
        val route = backStackEntry.toRoute<Category>()
        CategoryScreen(
            categoryId = route.id,
            categoryTitle = route.name,
            onNavigateUp = navController::navigateUp,
            onNavigateScreen = navController::navigate
        )
    }
}