package com.dvx.news.ui.screens.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dvx.news.ui.screens.OverviewScreen
import kotlinx.serialization.Serializable

@Serializable
data object Overview

internal fun NavGraphBuilder.overviewDestination(navController: NavController) {
    composable<Overview> { OverviewScreen(onNavigateScreen = navController::navigate) }
}