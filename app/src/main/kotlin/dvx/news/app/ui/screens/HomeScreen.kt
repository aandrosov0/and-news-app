package dvx.news.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.rememberAsyncImagePainter
import dvx.news.app.R
import dvx.news.app.states.ArticleUiState
import dvx.news.app.states.Destination
import dvx.news.app.states.ErrorUiState
import dvx.news.app.themes.DVXTheme
import dvx.news.app.ui.components.DVXTopLogoAppBar
import dvx.news.app.ui.components.ErrorBox
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

    Scaffold(
        modifier = modifier,
        topBar = { DVXTopLogoAppBar() },
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    ) { paddings ->
        PullToRefreshBox(
            isRefreshing = uiState.isLoading,
            onRefresh = { homeViewModel.getAll(refresh = true) },
            modifier = Modifier.padding(paddings)
        ) {
            HomeContent(
                modifier = Modifier.fillMaxSize(),
                error = uiState.error,
                articles = uiState.recentArticles,
                onArticleClick = { navController.navigate(Destination.Article) }
            )
        }
    }
}

@Composable
private fun HomeContent(
    modifier: Modifier = Modifier.fillMaxSize(),
    error: ErrorUiState? = null,
    articles: List<ArticleUiState> = listOf(),
    onArticleClick: (ArticleUiState) -> Unit = {}
) {
    if (error != null) {
        ErrorBox(
            icon = painterResource(error.iconId),
            error = stringResource(error.messageId),
            action = stringResource(error.actionId),
            onActionClick = error.onAction,
            modifier = modifier.verticalScroll(rememberScrollState())
        )
        return
    }

    ArticlesList(
        articles = articles,
        onArticleClick = onArticleClick,
        modifier = modifier
    )
}

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
