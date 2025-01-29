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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import dvx.news.app.R
import dvx.news.app.ui.states.ArticleUiState
import dvx.news.app.ui.states.RefreshableScreenState
import dvx.news.app.ui.themes.DVXTheme
import dvx.news.app.ui.components.DVXTopLogoAppBar
import dvx.news.app.ui.components.Screen
import dvx.news.app.ui.components.Thumbnail
import dvx.news.app.ui.components.VerticalPost
import dvx.news.app.ui.screens.navigation.Article
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
        HomeContent(
            articles = uiState.recentArticles,
            onArticleClick = { navController.navigate(Article(id = it.id)) },
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
    val thumbnailStep = 3
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(14.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(
            items = articles,
            span = { index, _ ->
                if (index % thumbnailStep == 0) {
                    GridItemSpan(maxLineSpan)
                } else {
                    GridItemSpan(1)
                }
            }
        ) { index, article ->
            val painter = rememberAsyncImagePainter(
                model = article.imageUrl,
                contentScale = ContentScale.FillWidth
            )
            val state by painter.state.collectAsState()
            val image = when (state) {
                is AsyncImagePainter.State.Success -> painter
                else -> painterResource(R.drawable.img_small_preview)
            }

            if (index % thumbnailStep == 0) {
                Thumbnail(
                    image = image,
                    onClick = { onArticleClick(article) },
                    headline = article.headline,
                    subheadline = article.subheadline,
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                VerticalPost(
                    image = image,
                    onClick = { onArticleClick(article) },
                    modifier = Modifier
                        .fillMaxWidth(),
                    title = article.subheadline,
                    description = article.headline
                )
            }
        }
    }
}

@Preview
@Composable
private fun HomeContentPreview() {
    DVXTheme {
        HomeContent(
            articles = buildList {
                repeat(10) {
                    add(ArticleUiState())
                }
            },
            onArticleClick = {}
        )
    }
}
