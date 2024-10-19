package dvx.news.app.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dvx.news.app.R
import dvx.news.app.components.DVXTopAppBar
import dvx.news.app.components.Tabs
import dvx.news.app.components.sections.NewsAllSection
import dvx.news.app.components.sections.NewsHeadlinesSection
import dvx.news.app.states.Destination
import dvx.news.app.states.NewsTab
import dvx.news.app.themes.DVXTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(
    initialTab: NewsTab,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val tabs = NewsTab.entries
    val composableCoroutine = rememberCoroutineScope()

    val pagerState = rememberPagerState(
        initialPage = tabs.indexOf(initialTab),
        pageCount = { tabs.size }
    )

    Column(modifier = modifier) {
        Column(modifier = modifier) {
            DVXTopAppBar(
                title = "",
                destination = stringResource(R.string.menu),
                onBack = navController::navigateUp
            )
            Tabs(
                tabs = tabs,
                currentTab = tabs[pagerState.currentPage],
                onTabSelect = {
                    composableCoroutine.launch { pagerState.animateScrollToPage(tabs.indexOf(it))  }
                }
            )
        }
        HorizontalPager(
            state = pagerState,
            verticalAlignment = Alignment.Top,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            when (tabs[page]) {
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