package com.dvx.news.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dvx.news.ui.components.DVXBottomNavigation
import com.dvx.news.ui.components.TopLevelRoute
import com.dvx.news.ui.screens.SplashScreen
import com.dvx.news.ui.screens.navigation.AppNavigation
import com.dvx.news.ui.screens.navigation.Category
import com.dvx.news.ui.screens.navigation.Home
import com.dvx.news.ui.screens.navigation.Overview
import com.dvx.news.ui.themes.DVXTheme
import com.dvx.news.ui.viewModels.MainViewModel
import com.dvx.news.R
import com.dvx.news.ui.states.iconResId
import org.koin.compose.koinInject

@Composable
fun App(mainViewModel: MainViewModel = koinInject()) {
    LaunchedEffect(Unit) { mainViewModel.load() }
    val uiState by mainViewModel.uiState.collectAsState()

    if (uiState.isLoading || uiState.error != null) {
        SplashScreen(
            error = uiState.error,
            onRetryClick = mainViewModel::load
        )
    } else {
        DVXTheme(theme = uiState.settings.theme) {
            AppContent()
        }
    }
}

@Composable
private fun AppContent(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = koinInject(),
    navController: NavHostController = rememberNavController()
) {
    val uiState by mainViewModel.uiState.collectAsState()
    val topLevelRoutes = buildList {
        add(
            TopLevelRoute(
                name = stringResource(R.string.home),
                icon = painterResource(R.drawable.ic_home),
                route = Home
            )
        )
        for ((index, category) in uiState.categories.withIndex()) {
            val route = Category(id = category.id, name = category.name)
            add(
                TopLevelRoute(
                    name = route.name,
                    icon = painterResource(category.iconResId),
                    route = route
                )
            )
            if (index >= 2) { break }
        }
        add(
            TopLevelRoute(
                name = stringResource(R.string.overview),
                icon = painterResource(R.drawable.ic_mehr),
                route = Overview
            )
        )
    }

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination?.hierarchy?.firstOrNull()

    Scaffold(
        modifier = modifier,
        bottomBar = {
            DVXBottomNavigation(
                currentDestination = currentDestination,
                onDestinationChange = {
                    navController.navigate(it) {
                        launchSingleTop = true
                        popUpTo<Home>()
                    }
                },
                destinations = topLevelRoutes
            )
        },
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
    ) { innerPaddings ->
        AppNavigation(
            modifier = Modifier.padding(bottom = innerPaddings.calculateBottomPadding()),
            navController = navController
        )
    }
}