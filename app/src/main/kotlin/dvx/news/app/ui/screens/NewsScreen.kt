package dvx.news.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import dvx.news.app.R
import dvx.news.app.ui.components.DVXTopAppBar
import dvx.news.app.ui.components.HorizontalTabPager
import dvx.news.app.ui.components.NewsComprehensiveItem
import dvx.news.app.ui.components.NewsItem
import dvx.news.app.ui.components.NewsItemsSection
import dvx.news.app.ui.components.Screen
import dvx.news.app.ui.screens.navigation.Article
import dvx.news.app.ui.states.ArticleUiState
import dvx.news.app.ui.states.CategoryUiState
import dvx.news.app.ui.states.NewsTab
import dvx.news.app.ui.states.RefreshableScreenState
import dvx.news.app.ui.themes.DVXTheme
import dvx.news.app.ui.viewModels.NewsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun NewsScreen(
    initialTab: NewsTab,
    navController: NavController,
    modifier: Modifier = Modifier,
    newsViewModel: NewsViewModel = koinViewModel(),
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
        topBar = {
            DVXTopAppBar(
                title = stringResource(R.string.news),
                onBackClick = navController::navigateUp
            )
        },
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
                        onArticleClick = { navController.navigate(Article(id = it.id)) },
                        modifier = Modifier.padding(horizontal = 16.dp),
                        articles = uiState.randomArticles,
                        categories = uiState.categories
                    )
                }
                NewsTab.HEADERS -> {
                    HeadlinesNews(
                        onArticleClick = { navController.navigate(Article(id = it.id)) },
                        modifier = Modifier.padding(horizontal = 16.dp),
                        articles = uiState.recentArticles,
                        categories = uiState.categories
                    )
                }
            }
        }
    }
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
        val state by painter.state.collectAsState()

        NewsItem(
            time = it.time,
            type = categories.find { category -> category.id == it.categoryId }?.name ?: "",
            image = when (state) {
                is AsyncImagePainter.State.Success -> painter
                else -> painterResource(R.drawable.img_small_preview)
            },
            headline = it.headline,
            subheadline = it.subheadline,
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
        val painter = rememberAsyncImagePainter(it.imageUrl)
        val state by painter.state.collectAsState()
        val image = when (state) {
            is AsyncImagePainter.State.Success -> painter
            else -> painterResource(R.drawable.img_small_preview)
        }

        NewsComprehensiveItem(
            time = it.time,
            type = categories.find { category -> category.id == it.categoryId }?.name ?: "",
            image = image,
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