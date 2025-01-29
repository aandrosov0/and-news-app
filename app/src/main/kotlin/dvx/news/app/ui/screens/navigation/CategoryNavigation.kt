package dvx.news.app.ui.screens.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import dvx.news.app.ui.states.CategoryUiState
import dvx.news.app.ui.screens.CategoryScreen
import kotlinx.serialization.Serializable

@Serializable
data class Category(val id: Long, val name: String)

internal fun NavGraphBuilder.categoryDestination(navController: NavController) {
    composable<Category> { backStackEntry ->
        val route = backStackEntry.toRoute<Category>()
        CategoryScreen(navController = navController, category = CategoryUiState(route.id, route.name))
    }
}