package dvx.news.app.components.sections

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dvx.news.app.R
import dvx.news.app.components.NewsItem
import dvx.news.app.states.News
import dvx.news.app.themes.DVXTheme

@Composable
fun NewsAllSection(
    onNews: () -> Unit,
    modifier: Modifier = Modifier,
    news: List<News> = newsList
) {
    NewsItemsSection(
        title = stringResource(R.string.haders_tab_title),
        items = news,
        modifier = modifier
    ) {
        NewsItem(
            time = it.time,
            type = it.type,
            imageId = R.drawable.post_img_preview,
            onClick = onNews
        )
    }
}

@Preview
@Composable
private fun NewsAllSectionPreview() = DVXTheme {
    NewsAllSection(
        news = newsList,
        onNews = {}
    )
}