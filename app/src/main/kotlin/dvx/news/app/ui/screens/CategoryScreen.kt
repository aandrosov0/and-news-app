package dvx.news.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import dvx.news.app.R
import dvx.news.app.ui.components.DVXTopAppBar
import dvx.news.app.ui.components.Screen
import dvx.news.app.ui.components.Thumbnail
import dvx.news.app.ui.components.VerticalPost
import dvx.news.app.ui.screens.navigation.Article
import dvx.news.app.ui.states.ArticleUiState
import dvx.news.app.ui.states.CategoryUiState
import dvx.news.app.ui.states.RefreshableScreenState
import dvx.news.app.ui.themes.DVXTheme
import dvx.news.app.ui.viewModels.CategoryViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun CategoryScreen(
    category: CategoryUiState,
    navController: NavController,
    modifier: Modifier = Modifier,
    categoryViewModel: CategoryViewModel = koinViewModel(),
) {
    LaunchedEffect(Unit) { categoryViewModel.get(category.id) }
    val uiState by categoryViewModel.uiState.collectAsState()

    Screen(
        modifier = modifier,
        state = RefreshableScreenState(
            error = uiState.error,
            isRefreshing = uiState.isLoading,
            onRefresh = { categoryViewModel.get(categoryId = category.id, refresh = true) }
        ),
        topBar = {
            DVXTopAppBar(
                title = category.name,
                onBackClick = navController::navigateUp
            )
        },
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    ) {
        CategoryContent(
            title = category.name,
            articles = uiState.articles,
            onArticleClick = { navController.navigate(Article(id = it.id)) }
        )
    }
}

@Composable
private fun CategoryContent(
    title: String,
    articles: List<ArticleUiState>,
    onArticleClick: (ArticleUiState) -> Unit,
    modifier: Modifier = Modifier,
) {
    val step = 3
    val headerArticles = listOf(articles.elementAtOrNull(0))
    val bodyArticles = articles.subtract(headerArticles.toSet()).filterNotNull()
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(11.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            if (headerArticles[0] != null) {
                Header(
                    title = title,
                    overTitleArticle = headerArticles[0],
                    onArticleClick = onArticleClick
                )
            }
        }
        itemsIndexed(items = bodyArticles, span = { index, _ ->
            if (index % step == 0) {
                GridItemSpan(maxLineSpan)
            } else {
                GridItemSpan(1)
            }
        }) { index, article ->
            BodyItem(
                thumbnail = index % step == 0,
                article = article,
                onArticleClick = onArticleClick,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        item(span = { GridItemSpan(maxLineSpan) }) { Footer() }
    }
}

@Composable
private fun Header(
    title: String,
    modifier: Modifier = Modifier,
    overTitleArticle: ArticleUiState? = null,
    subTitleArticle: ArticleUiState? = null,
    onArticleClick: (ArticleUiState) -> Unit = {}
) {
    @Composable
    fun Article(
        article: ArticleUiState,
        onArticleClick: (ArticleUiState) -> Unit,
        modifier: Modifier = Modifier
    ) {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(article.imageUrl)
                .crossfade(true)
                .build()
        )
        val state by painter.state.collectAsState()

        VerticalPost(
            image = when (state) {
                is AsyncImagePainter.State.Success -> painter
                else -> painterResource(R.drawable.img_small_preview)
            },
            onClick = { onArticleClick(article) },
            modifier = modifier.fillMaxWidth()
        )
    }

    Column(modifier = modifier) {
        if (overTitleArticle != null) {
            Article(
                article = overTitleArticle,
                onArticleClick = onArticleClick,
            )
        }
        Title(name = title)
        if (subTitleArticle != null) {
            Article(
                article = subTitleArticle,
                onArticleClick = onArticleClick,
            )
        }
    }
}

@Composable
private fun BodyItem(
    thumbnail: Boolean,
    article: ArticleUiState,
    onArticleClick: (ArticleUiState) -> Unit,
    modifier: Modifier = Modifier,
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(article.imageUrl)
            .crossfade(true)
            .build()
    )
    val state by painter.state.collectAsState()
    val image = when (state) {
        is AsyncImagePainter.State.Success -> painter
        else -> painterResource(R.drawable.img_small_preview)
    }

    if (thumbnail) {
        Thumbnail(
            image = image,
            headline = article.headline,
            subheadline = article.subheadline,
            onClick = { onArticleClick(article) }
        )
    } else {
        VerticalPost(
            title = article.subheadline,
            description = article.headline,
            image = image,
            onClick = { onArticleClick(article) },
            modifier = modifier
        )
    }
}

@Composable
private fun Footer(modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.height(103.dp))
}

@Composable
private fun Title(
    name: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(
                horizontal = 10.dp,
                vertical = 20.dp
            )
    ) {
        Text(
            text = name.uppercase(),
            fontSize = 34.sp,
            fontWeight = FontWeight.ExtraBold,
        )
        Icon(
            imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.64f),
            modifier = Modifier
                .padding(start = 13.dp)
                .size(28.dp)
        )
    }
}

@Preview
@Composable
private fun TitlePreview() {
    DVXTheme {
        Title(name = "Food")
    }
}