package com.dvx.news.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Home,
        modifier = modifier
    ) {
        homeDestination(navController = navController)
        categoryDestination(navController = navController)
        overviewDestination(navController = navController)
        articleDestination(navController = navController)
        newsDestination(navController = navController)
        settingsDestination(navController = navController)
        includesDestination(navController = navController)
    }
}