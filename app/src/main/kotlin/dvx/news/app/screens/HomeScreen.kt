package dvx.news.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dvx.news.app.themes.DVXTheme
import dvx.news.app.viewModels.HomeViewModel
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import dvx.news.app.appModule
import dvx.news.app.components.HomeArticleImage
import dvx.news.data.dataOfflineModule
import org.koin.compose.KoinApplication

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = koinViewModel()
) {
    LaunchedEffect(Unit) {
        homeViewModel.getArticles()
    }

    val homeArticles by homeViewModel.uiState.collectAsState()
    PullToRefreshBox(
        isRefreshing = homeArticles.isLoading,
        onRefresh = { homeViewModel.getArticles(refresh = true) },
        modifier = modifier.fillMaxSize(),
        state = rememberPullToRefreshState(),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .fillMaxSize()
        ) {
            for (article in homeArticles.articles) {
                HomeArticleImage(
                    imageUrl = article.imageUrl,
                    onClick = { }
                )
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() = DVXTheme {
    KoinApplication(
        application = { modules(appModule, dataOfflineModule) }
    ) {
        HomeScreen(
            navController = rememberNavController()
        )
    }
}