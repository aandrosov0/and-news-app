package dvx.news.app.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dvx.news.app.R
import dvx.news.app.components.DVXTopAppBar
import dvx.news.app.components.HorizontalTabPager
import dvx.news.app.components.sections.NewsAllSection
import dvx.news.app.components.sections.NewsHeadlinesSection
import dvx.news.app.states.Destination
import dvx.news.app.states.NewsTab
import dvx.news.app.themes.DVXTheme

@Composable
fun NewsScreen(
    initialTab: NewsTab,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        DVXTopAppBar(
            title = "",
            destination = stringResource(R.string.menu),
            onNavigationClicked = navController::navigateUp
        )
        HorizontalTabPager(
            tabs = NewsTab.entries,
            initialTab = initialTab
        ) { tab ->
            when (tab) {
                NewsTab.ALL_NEWS -> NewsAllSection(
                    onNews = { navController.navigate(Destination.Article) },
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                NewsTab.HEADERS -> NewsHeadlinesSection(
                    onNews = { navController.navigate(Destination.Article) },
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
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