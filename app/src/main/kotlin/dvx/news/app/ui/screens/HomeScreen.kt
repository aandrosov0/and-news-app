package dvx.news.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import dvx.news.app.R
import dvx.news.app.states.ArticleUiState
import dvx.news.app.themes.DVXTheme
import dvx.news.app.viewModels.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
private fun ArticlesList(
    articles: List<ArticleUiState>,
    onArticleClick: (ArticleUiState) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        items(articles) { article ->
            val painter = if (LocalInspectionMode.current) {
                painterResource(R.drawable.img_preview)
            } else {
                rememberAsyncImagePainter(
                    model = article.imageUrl,
                    contentScale = ContentScale.FillWidth,
                )
            }
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { onArticleClick(article) }),
                contentScale = ContentScale.FillWidth,
            )
        }
    }
}

@Preview
@Composable
private fun ArticlesListPreview() {
    DVXTheme {
        ArticlesList(
            articles = listOf(
                ArticleUiState(),
                ArticleUiState(),
                ArticleUiState(),
            ),
            onArticleClick = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = koinViewModel()
) {
    LaunchedEffect(Unit) { homeViewModel.getAll() }
    val uiState by homeViewModel.uiState.collectAsState()

    PullToRefreshBox(
        isRefreshing = uiState.isLoading,
        onRefresh = { homeViewModel.getAll(refresh = true) },
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant),
    ) {
        ArticlesList(
            articles = uiState.recentArticles,
            onArticleClick = { }
        )
    }
}