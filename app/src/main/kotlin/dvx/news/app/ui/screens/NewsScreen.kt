package dvx.news.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
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
import coil3.compose.rememberAsyncImagePainter
import dvx.news.app.R
import dvx.news.app.states.ArticleUiState
import dvx.news.app.states.Destination
import dvx.news.app.states.NewsTab
import dvx.news.app.themes.DVXTheme
import dvx.news.app.ui.components.ErrorBox
import dvx.news.app.ui.components.HorizontalTabPager
import dvx.news.app.ui.components.NewsComprehensiveItem
import dvx.news.app.ui.components.NewsItem
import dvx.news.app.ui.components.NewsItemsSection
import dvx.news.app.viewModels.NewsViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(
    initialTab: NewsTab,
    navController: NavController,
    modifier: Modifier = Modifier,
    newsViewModel: NewsViewModel = koinViewModel()
) {
    LaunchedEffect(Unit) { newsViewModel.getAll() }
    val uiState by newsViewModel.uiState.collectAsState()

    PullToRefreshBox(
        isRefreshing = uiState.isLoading,
        onRefresh = { newsViewModel.getAll(true) }
    ) {
        HorizontalTabPager(
            tabs = NewsTab.entries,
            initialTab = initialTab,
            modifier = modifier
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .fillMaxSize()
        ) { tab ->
            uiState.error?.let {
                ErrorBox(
                    icon = painterResource(uiState.error!!.iconId),
                    error = stringResource(uiState.error!!.messageId),
                    action = stringResource(uiState.error!!.actionId),
                    onActionClick = uiState.error!!.onAction,
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                )
                return@HorizontalTabPager
            }
            when (tab) {
                NewsTab.ALL_NEWS -> {
                    AllNews(
                        onNews = { navController.navigate(Destination.Article) },
                        modifier = Modifier.padding(horizontal = 16.dp),
                        news = uiState.randomArticles
                    )
                }
                NewsTab.HEADERS -> {
                    HeadlinesNews(
                        onNews = { navController.navigate(Destination.Article) },
                        modifier = Modifier.padding(horizontal = 16.dp),
                        news = uiState.recentArticles
                    )
                }
            }
        }
    }
}

@Composable
private fun AllNews(
    onNews: () -> Unit,
    news: List<ArticleUiState>,
    modifier: Modifier = Modifier,
) {
    NewsItemsSection(
        title = stringResource(R.string.haders_tab_title),
        items = news,
        modifier = modifier
    ) {
        val painter = rememberAsyncImagePainter(it.imageUrl)
        NewsItem(
            time = it.time,
            type = "TODO",
            image = painter,
            onClick = onNews
        )
    }
}

@Composable
private fun HeadlinesNews(
    onNews: () -> Unit,
    news: List<ArticleUiState>,
    modifier: Modifier = Modifier,
) {
    NewsItemsSection(
        title = stringResource(R.string.news_tab_title),
        items = news,
        modifier = modifier
    ) {
        var painter = rememberAsyncImagePainter(it.imageUrl)
        NewsComprehensiveItem(
            time = it.time,
            type = "TODO",
            image = painter,
            title = it.headline,
            description = it.leadParagraph,
            onClick = { onNews() }
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