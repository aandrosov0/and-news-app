package dvx.news.app.components.sections

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dvx.news.app.R
import dvx.news.app.components.NewsComprehensiveItem
import dvx.news.app.states.News
import dvx.news.app.themes.DVXTheme

val newsList = listOf(
    News(
        time = "12:38 Uhr",
        type = "Regional",
        title = "Polizei-Grobeinsatz in Radeberg",
        description = "Polizei schiebt Messer-Angreifer (54) ins",),
    News(
        time = "12:00 Uhr",
        type = "Leben",
        title = "Polizei-Grobeinsatz in Radeberg",
        description = "Polizei schiebt Messer-Angreifer (54) ins",),
)

@Composable
fun NewsHeadlinesSection(
    onNews: () -> Unit,
    modifier: Modifier = Modifier,
    news: List<News> = newsList
) {
    NewsItemsSection(
        title = stringResource(R.string.news_tab_title),
        items = news,
        modifier = modifier
    ) {
        NewsComprehensiveItem(
            time = it.time,
            type = it.type,
            imageId = R.drawable.post_img_preview,
            title = it.title,
            description = it.description,
            onClick = { onNews() }
        )
    }
}

@Preview
@Composable
private fun NewsHeadlinesSectionPreview() = DVXTheme {
    NewsHeadlinesSection(
        news = newsList,
        onNews = {}
    )
}