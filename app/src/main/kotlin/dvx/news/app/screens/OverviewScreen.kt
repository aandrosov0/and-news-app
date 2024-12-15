package dvx.news.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dvx.news.app.appModule
import dvx.news.app.components.sections.OverviewActionsSection
import dvx.news.app.components.sections.OverviewCategoriesSection
import dvx.news.app.components.sections.OverviewTopicsSection
import dvx.news.app.states.Destination
import dvx.news.app.states.NewsTab
import dvx.news.app.themes.DVXTheme
import dvx.news.app.viewModels.CategoriesViewModel
import dvx.news.data.dataOfflineModule
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinApplication
import androidx.compose.runtime.getValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OverviewScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    categoriesViewModel: CategoriesViewModel = koinViewModel()
) {
    LaunchedEffect(Unit) {
        categoriesViewModel.getAll()
    }

    val categoriesUiState by categoriesViewModel.uiState.collectAsState()

    PullToRefreshBox(
        isRefreshing = categoriesUiState.isLoading,
        onRefresh = { categoriesViewModel.getAll(refresh = true) }
    ) {
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(
                    horizontal = 10.dp,
                    vertical = 20.dp
                )
        ) {
            OverviewActionsSection(
                onProfile = { },
                onSettings = { navController.navigate(Destination.Settings) },
            )
            OverviewTopicsSection(
                onHeadlines = { navController.navigate(Destination.News(NewsTab.HEADERS)) },
                onNews = { navController.navigate(Destination.News(NewsTab.ALL_NEWS)) },
                onIncludes = { navController.navigate(Destination.Includes) }
            )
            OverviewCategoriesSection(
                categories = categoriesUiState.categories,
                onClick = {}
            )
        }
    }
}


@Preview
@Composable
private fun OverviewScreenPreview() = DVXTheme {
    KoinApplication(application = { modules(dataOfflineModule, appModule) }) {
        OverviewScreen(
            navController = rememberNavController()
        )
    }
}