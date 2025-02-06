package dvx.news.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dvx.news.app.ui.components.DVXTopLogoAppBar
import dvx.news.app.ui.components.Screen
import dvx.news.app.ui.components.feed
import dvx.news.app.ui.screens.navigation.Article
import dvx.news.app.ui.states.ArticleUiState
import dvx.news.app.ui.states.RefreshableScreenState
import dvx.news.app.ui.viewModels.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreen(
    navController: NavController,
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
        Content(
            articles = uiState.recentArticles,
            onArticleClick = { navController.navigate(Article(id = it.id)) },
            modifier = modifier,
        )
    }
}

@Composable
private fun Content(
    articles: List<ArticleUiState>,
    onArticleClick: (ArticleUiState) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(14.dp),
        horizontalArrangement = Arrangement.spacedBy((-8).dp),
    ) {
        feed(
            articles = articles,
            onArticleClick = onArticleClick
        )
    }
}