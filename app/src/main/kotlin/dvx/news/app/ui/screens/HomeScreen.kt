package dvx.news.app.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import dvx.news.app.ui.components.DVXTopLogoAppBar
import dvx.news.app.ui.components.Feed
import dvx.news.app.ui.components.Screen
import dvx.news.app.ui.screens.navigation.Article
import dvx.news.app.ui.states.RefreshableScreenState
import dvx.news.app.ui.viewModels.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreen(
    onNavigateScreen: (Any) -> Unit,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = koinViewModel(),
) {
    LaunchedEffect(Unit) { homeViewModel.getAll() }
    val uiState by homeViewModel.uiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Screen(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        state = RefreshableScreenState(
            error = uiState.error,
            isRefreshing = uiState.isLoading,
            onRefresh = { homeViewModel.getAll(refresh = true) }
        ),
        topBar = { DVXTopLogoAppBar(scrollBehavior = scrollBehavior) },
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Feed(
            articles = uiState.recentArticles,
            onArticleClick = { onNavigateScreen(Article(id = it.id)) }
        )
    }
}