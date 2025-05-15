package com.dvx.news.ui.screens.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dvx.news.ui.screens.HomeScreen
import kotlinx.serialization.Serializable

@Serializable
data object Home

internal fun NavGraphBuilder.homeDestination(navController: NavController) {
    composable<Home> { HomeScreen(onNavigateScreen = navController::navigate) }
}