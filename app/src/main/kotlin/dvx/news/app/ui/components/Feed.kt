package dvx.news.app.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemSpanScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import dvx.news.app.R
import dvx.news.app.ui.states.ArticleUiState

private const val THUMBNAIL_STEP = 3

fun LazyGridScope.feed(
    articles: List<ArticleUiState>,
    onArticleClick: (ArticleUiState) -> Unit,
) {
    itemsIndexed(
        items = articles,
        span = { index, _ -> determineSpan(index) }
    ) { index, article ->
        val image = rememberAsyncImagePainter(
            model = article.imageUrl,
            placeholder = painterResource(R.drawable.img_rectangle_preview),
        )

        if (index % THUMBNAIL_STEP == 0) {
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
                headline = article.headline,
                subheadline = article.subheadline,
                onClick = { onArticleClick(article) },
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

private fun LazyGridItemSpanScope.determineSpan(index: Int): GridItemSpan {
    val max = GridItemSpan(maxLineSpan)
    val min = GridItemSpan(1)
    return if (index % THUMBNAIL_STEP == 0) max else min
}