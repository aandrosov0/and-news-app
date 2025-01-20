package dvx.news.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.rememberAsyncImagePainter
import dvx.news.app.R
import dvx.news.app.states.ArticleScreen
import dvx.news.app.states.ArticleUiState
import dvx.news.app.states.RefreshableScreenState
import dvx.news.app.themes.DVXTheme
import dvx.news.app.ui.components.DVXTopLogoAppBar
import dvx.news.app.ui.components.Screen
import dvx.news.app.ui.components.VerticalPost
import dvx.news.app.viewModels.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = koinViewModel(),
    navController: NavController = rememberNavController()
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
        HomeContent(
            articles = uiState.recentArticles,
            onArticleClick = { navController.navigate(ArticleScreen(id = it.id)) },
            modifier = modifier,
        )
    }
}

@Composable
private fun HomeContent(
    articles: List<ArticleUiState>,
    onArticleClick: (ArticleUiState) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        itemsIndexed(
            items = articles,
            span = { _, _ ->
                GridItemSpan(2)
            }
        ) { _, article ->
            val painter = if (LocalInspectionMode.current) {
                painterResource(R.drawable.img_preview)
            } else {
                rememberAsyncImagePainter(
                    model = article.imageUrl,
                    contentScale = ContentScale.FillWidth,
                )
            }
            VerticalPost(
                image = painter,
                onClick = { onArticleClick(article) },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
private fun HomeContentPreview() {
    DVXTheme {
        HomeContent(
            articles = listOf(
                ArticleUiState(),
                ArticleUiState(),
                ArticleUiState(),
            ),
            onArticleClick = {}
        )
    }
}
