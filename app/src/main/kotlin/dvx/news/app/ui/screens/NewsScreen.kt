package dvx.news.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.rememberAsyncImagePainter
import dvx.news.app.R
import dvx.news.app.states.ArticleUiState
import dvx.news.app.states.CategoryUiState
import dvx.news.app.states.Destination
import dvx.news.app.states.NewsTab
import dvx.news.app.states.RefreshableScreenState
import dvx.news.app.themes.DVXTheme
import dvx.news.app.ui.components.BackButton
import dvx.news.app.ui.components.HorizontalTabPager
import dvx.news.app.ui.components.NewsComprehensiveItem
import dvx.news.app.ui.components.NewsItem
import dvx.news.app.ui.components.NewsItemsSection
import dvx.news.app.ui.components.Screen
import dvx.news.app.viewModels.NewsViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(
    initialTab: NewsTab,
    modifier: Modifier = Modifier,
    newsViewModel: NewsViewModel = koinViewModel(),
    navController: NavController = rememberNavController()
) {
    LaunchedEffect(Unit) { newsViewModel.getAll() }
    val uiState by newsViewModel.uiState.collectAsState()

    Screen(
        modifier = modifier,
        state = RefreshableScreenState(
            error = uiState.error,
            isRefreshing = uiState.isLoading,
            onRefresh = { newsViewModel.getAll(refresh = true) },
        ),
        topBar = { NewsTopBar(onBackClick = navController::navigateUp) },
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    ) {
        HorizontalTabPager(
            tabs = NewsTab.entries,
            initialTab = initialTab,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .fillMaxSize()
        ) { tab ->
            when (tab) {
                NewsTab.ALL_NEWS -> {
                    AllNews(
                        onArticleClick = { navController.navigate(Destination.Article(id = it.id)) },
                        modifier = Modifier.padding(horizontal = 16.dp),
                        articles = uiState.randomArticles,
                        categories = uiState.categories
                    )
                }
                NewsTab.HEADERS -> {
                    HeadlinesNews(
                        onArticleClick = { navController.navigate(Destination.Article(id = it.id)) },
                        modifier = Modifier.padding(horizontal = 16.dp),
                        articles = uiState.recentArticles,
                        categories = uiState.categories
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NewsTopBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = "News",
                style = MaterialTheme.typography.bodyMedium,
            )
        },
        navigationIcon = {
            BackButton(
                onClick = onBackClick,
                icon = R.drawable.ic_arrow_left,
                label = "Mehr"
            )
        }
    )
}

@Composable
private fun AllNews(
    articles: List<ArticleUiState>,
    onArticleClick: (ArticleUiState) -> Unit,
    categories: List<CategoryUiState>,
    modifier: Modifier = Modifier,
) {
    NewsItemsSection(
        title = stringResource(R.string.haders_tab_title),
        items = articles,
        modifier = modifier
    ) {
        val painter = rememberAsyncImagePainter(it.imageUrl)
        NewsItem(
            time = it.time,
            type = categories.find { category -> category.id == it.categoryId }!!.name,
            image = painter,
            onClick = { onArticleClick(it) }
        )
    }
}

@Composable
private fun HeadlinesNews(
    articles: List<ArticleUiState>,
    onArticleClick: (ArticleUiState) -> Unit,
    categories: List<CategoryUiState>,
    modifier: Modifier = Modifier,
) {
    NewsItemsSection(
        title = stringResource(R.string.news_tab_title),
        items = articles,
        modifier = modifier
    ) {
        var painter = rememberAsyncImagePainter(it.imageUrl)
        NewsComprehensiveItem(
            time = it.time,
            type = categories.find { category -> category.id == it.categoryId }!!.name,
            image = painter,
            title = it.subheadline,
            description = it.headline,
            onClick = { onArticleClick(it) }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NewsScreenPreview() = DVXTheme {
    NewsScreen(
        initialTab = NewsTab.ALL_NEWS,
        navController = rememberNavController(),
        modifier = Modifier.fillMaxWidth()
    )
}