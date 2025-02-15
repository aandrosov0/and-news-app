package dvx.news.app.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemSpanScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import dvx.news.app.R
import dvx.news.app.ui.states.ArticleUiState

private const val THUMBNAIL_STEP = 3

@Composable
fun Feed(
    articles: List<ArticleUiState>,
    onArticleClick: (ArticleUiState) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 103.dp),
        verticalArrangement = Arrangement.spacedBy(11.dp),
        horizontalArrangement = Arrangement.spacedBy((-8).dp),
    ) {
        feed(
            articles = articles,
            onArticleClick = onArticleClick
        )
    }
}

fun LazyGridScope.feed(
    articles: List<ArticleUiState>,
    onArticleClick: (ArticleUiState) -> Unit,
) {
    itemsIndexed(
        items = articles,
        span = { index, _ -> determineSpan(index) }
    ) { index, article ->
        val image = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .size(640, 480)
                .data(article.imageUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.img_rectangle_preview),
        )
        if (index % THUMBNAIL_STEP == 0) {
            Thumbnail(
                image = image,
                headline = article.headline,
                subheadline = article.subheadline,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onArticleClick(article) }
            )
        } else {
            VerticalPost(
                image = image,
                headline = article.headline,
                subheadline = article.subheadline,
                onClick = { onArticleClick(article) },
                modifier = Modifier.padding(horizontal = 8.dp),
                minimized = true
            )
        }
    }
}

private fun LazyGridItemSpanScope.determineSpan(index: Int): GridItemSpan {
    val max = GridItemSpan(maxLineSpan)
    val min = GridItemSpan(1)
    return if (index % THUMBNAIL_STEP == 0) max else min
}