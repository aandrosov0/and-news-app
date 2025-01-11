package dvx.news.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import dvx.news.app.R
import dvx.news.app.states.ArticleUiState
import dvx.news.app.states.CategoryUiState
import dvx.news.app.states.Destination
import dvx.news.app.themes.DVXTheme
import dvx.news.app.ui.components.BackButton
import dvx.news.app.ui.components.ErrorBox
import dvx.news.app.ui.components.VerticalPost
import dvx.news.app.viewModels.CategoryViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    category: CategoryUiState,
    modifier: Modifier = Modifier,
    categoryViewModel: CategoryViewModel = koinViewModel(),
    navController: NavController = rememberNavController()
) {
    LaunchedEffect(Unit) { categoryViewModel.get(category.id) }
    val uiState by categoryViewModel.uiState.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = {
            CategoryTopBar(
                title = category.name,
                onBackClick = { navController.navigateUp() }
            )
        },
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    ) { paddings ->
        PullToRefreshBox(
            isRefreshing = uiState.isLoading,
            onRefresh = { categoryViewModel.get(category.id) },
            modifier = Modifier.fillMaxSize().padding(paddings)
        )  {
            if (!uiState.isLoading && uiState.error == null) {
                CategoryContent(
                    title = category.name,
                    articles = uiState.articles,
                    onArticleClick = { navController.navigate(Destination.Article(id = it.id)) }
                )
            }

            val error = uiState.error
            if (error != null) {
                ErrorBox(
                    icon = painterResource(error.iconId),
                    error = stringResource(error.messageId),
                    action = stringResource(error.actionId),
                    onActionClick = error.onAction,
                    modifier = modifier.fillMaxSize()
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CategoryTopBar(
    title: String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
            )
        },
        navigationIcon = {
            BackButton(
                onClick = onBackClick,
                icon = R.drawable.ic_arrow_left,
                label = "Mehr"
            )
        },
    )
}

@Composable
private fun CategoryContent(
    title: String,
    articles: List<ArticleUiState>,
    onArticleClick: (ArticleUiState) -> Unit,
    modifier: Modifier = Modifier,
) {
    val headerArticles = listOf(articles.elementAtOrNull(0), articles.elementAtOrNull(1))
    val bodyArticles = articles.subtract(headerArticles).filterNotNull()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(11.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            Header(
                title = title,
                overTitleArticle = headerArticles[0],
                subTitleArticle = headerArticles[1],
                onArticleClick = onArticleClick
            )
        }
        itemsIndexed(items = bodyArticles) { index, article ->
            val paddings = if ((index+1) % 2 == 0) {
                PaddingValues(end = 10.dp)
            } else {
                PaddingValues(start = 10.dp)
            }

            BodyItem(
                article = article,
                onArticleClick = onArticleClick,
                modifier = Modifier.padding(paddings)
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

    VerticalPost(
        title = article.subheadline,
        description = article.headline,
        image = when (state) {
            is AsyncImagePainter.State.Success -> painter
            else -> painterResource(R.drawable.img_small_preview)
        },
        onClick = { onArticleClick(article) },
        modifier = modifier
    )
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