package dvx.news.app.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import dvx.news.app.R
import dvx.news.app.ui.components.DVXTopAppBar
import dvx.news.app.ui.components.Screen
import dvx.news.app.ui.components.Tab
import dvx.news.app.ui.components.VerticalPostCard
import dvx.news.app.ui.screens.navigation.Article
import dvx.news.app.ui.states.ArticleUiState
import dvx.news.app.ui.states.CategoryUiState
import dvx.news.app.ui.states.NewsTab
import dvx.news.app.ui.states.RefreshableScreenState
import dvx.news.app.ui.states.localizedName
import dvx.news.app.ui.themes.DVXTheme
import dvx.news.app.ui.viewModels.NewsViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun NewsScreen(
    initialTab: NewsTab,
    onNavigateUp: () -> Unit,
    onNavigateScreen: (Any) -> Unit,
    modifier: Modifier = Modifier,
    newsViewModel: NewsViewModel = koinViewModel(),
) {
    LaunchedEffect(Unit) { newsViewModel.getAll() }
    val uiState by newsViewModel.uiState.collectAsState()

    val pages = NewsTab.entries
    val pagerState = rememberPagerState(
        initialPage = pages.indexOf(initialTab),
        pageCount = pages::size
    )

    val coroutineScope = rememberCoroutineScope()
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
                onBackClick = onNavigateUp,
                selectedTabIndex = pagerState.currentPage,
            ) {
                pages.forEachIndexed { index, page ->
                    Tab(
                        text = page.localizedName,
                        selected = pagerState.currentPage == index,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                    )
                }
            }
        }
    ) {
        HorizontalPager(state = pagerState) { page ->
            when (pages[page]) {
                NewsTab.ALL_NEWS -> NewsPage(
                    onArticleClick = { onNavigateScreen(Article(id = it.id)) },
                    modifier = Modifier.padding(horizontal = 16.dp),
                    articles = uiState.randomArticles,
                    categories = uiState.categories
                )
                NewsTab.HEADERS -> HeadlinesPage(
                    onArticleClick = { onNavigateScreen(Article(id = it.id)) },
                    modifier = Modifier.padding(horizontal = 16.dp),
                    articles = uiState.recentArticles,
                    categories = uiState.categories
                )
            }
        }
    }
}

@Composable
private fun NewsPage(
    articles: List<ArticleUiState>,
    onArticleClick: (ArticleUiState) -> Unit,
    categories: List<CategoryUiState>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        item { Title(text = stringResource(R.string.news_tab_title)) }
        items(items = articles) { article ->
            Item(
                imageUrl = article.imageUrl,
                headline = article.headline,
                subheadline = article.subheadline,
                createdAt = article.time,
                category = categories.find { it.id == article.categoryId }?.name ?: "",
                onClick = { onArticleClick(article) },
                thumbnail = true
            )
        }
    }
}

@Composable
private fun HeadlinesPage(
    articles: List<ArticleUiState>,
    onArticleClick: (ArticleUiState) -> Unit,
    categories: List<CategoryUiState>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        item { Title(text = stringResource(R.string.haders_tab_title)) }
        items(items = articles) { article ->
            Item(
                imageUrl = article.imageUrl,
                headline = article.headline,
                subheadline = article.subheadline,
                createdAt = article.time,
                category = categories.find { it.id == article.categoryId }?.name ?: "",
                onClick = { onArticleClick(article) }
            )
        }
    }
}

@Composable
private fun Title(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier
            .alpha(.64f)
            .padding(vertical = 20.dp)
            .fillMaxWidth(),
        fontSize = 16.sp,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
private fun Item(
    imageUrl: String,
    headline: String,
    subheadline: String,
    createdAt: String,
    category: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    thumbnail: Boolean = false,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(space = 12.dp),
        modifier = modifier.height(intrinsicSize = IntrinsicSize.Min)
    ) {
        VerticalDirectionLine(
            modifier = Modifier
                .offset(y = 23.dp)
                .fillMaxHeight()
        )
        VerticalPostCard(
            image = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.img_rectangle_preview)
            ),
            headline = headline,
            subheadline = subheadline,
            createdAt = createdAt,
            category = category,
            onClick = onClick,
            modifier = Modifier
                .padding(bottom = 10.dp)
                .fillMaxWidth(),
            thumbnail = thumbnail
        )
    }
}

@Composable
fun VerticalDirectionLine(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(width = 12.dp, height = 100.dp)) {
        drawLine(
            color = Color(0xffdadada),
            start = Offset(x = center.x, y = size.minDimension / 2.0f),
            end = Offset(x = center.x, y = size.maxDimension),
            strokeWidth = size.width * .1f
        )
        drawCircle(
            color = Color.Red,
            center = Offset(x = center.x, y = size.minDimension / 2.0f)
        )
    }
}

@Preview
@Composable
private fun VerticalDirectionLinePreview() = DVXTheme {
    VerticalDirectionLine()
}