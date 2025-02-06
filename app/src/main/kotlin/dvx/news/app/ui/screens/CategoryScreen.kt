package dvx.news.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import dvx.news.app.R
import dvx.news.app.ui.components.DVXTopAppBar
import dvx.news.app.ui.components.Screen
import dvx.news.app.ui.components.Thumbnail
import dvx.news.app.ui.components.feed
import dvx.news.app.ui.screens.navigation.Article
import dvx.news.app.ui.states.ArticleUiState
import dvx.news.app.ui.states.CategoryUiState
import dvx.news.app.ui.states.RefreshableScreenState
import dvx.news.app.ui.themes.DVXTheme
import dvx.news.app.ui.viewModels.CategoryViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CategoryScreen(
    category: CategoryUiState,
    navController: NavController,
    modifier: Modifier = Modifier,
    categoryViewModel: CategoryViewModel = koinViewModel(),
) {
    LaunchedEffect(Unit) { categoryViewModel.get(category.id) }
    val uiState by categoryViewModel.uiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Screen(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        state = RefreshableScreenState(
            error = uiState.error,
            isRefreshing = uiState.isLoading,
            onRefresh = { categoryViewModel.get(categoryId = category.id, refresh = true) }
        ),
        topBar = {
            DVXTopAppBar(
                onBackClick = navController::navigateUp,
                scrollBehavior = scrollBehavior,
                title = category.name
            )
        },
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Content(
            title = category.name,
            articles = uiState.articles,
            onArticleClick = { navController.navigate(Article(id = it.id)) }
        )
    }
}

@Composable
private fun Content(
    title: String,
    articles: List<ArticleUiState>,
    onArticleClick: (ArticleUiState) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 103.dp),
        verticalArrangement = Arrangement.spacedBy(11.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            Header(
                title = title,
                article = articles.elementAtOrNull(0),
                onArticleClick = onArticleClick
            )
        }
        feed(
            articles = articles.drop(1),
            onArticleClick = onArticleClick
        )
    }
}

@Composable
private fun Header(
    title: String,
    modifier: Modifier = Modifier,
    article: ArticleUiState? = null,
    onArticleClick: (ArticleUiState) -> Unit = {}
) {
    Column(modifier = modifier) {
        if (article != null) {
            Thumbnail(
                image = rememberAsyncImagePainter(
                    model = article.imageUrl,
                    placeholder = painterResource(R.drawable.img_rectangle_preview)
                ),
                headline = article.headline,
                subheadline = article.subheadline,
                onClick = { onArticleClick(article) },
            )
        }
        Title(text = title)
    }
}

@Composable
private fun Title(
    text: String,
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
            text = text.uppercase(),
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
        Title(text = "Food")
    }
}