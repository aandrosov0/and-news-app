package dvx.news.app.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dvx.news.app.R
import dvx.news.app.components.DVXTopAppBar
import dvx.news.app.components.HeadersTabContent
import dvx.news.app.components.NewsTabContent
import dvx.news.app.components.Tabs
import dvx.news.app.states.Destination
import dvx.news.app.states.Tab
import dvx.news.app.themes.DVXTheme

@Composable
fun NewsScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var currentTab by remember { mutableStateOf(Tab.ALL_NEWS) }

    Column(modifier = modifier
    ) {
        DVXTopAppBar(
            title = "",
            destination = stringResource(R.string.menu),
            onNavigateUp = navController::navigateUp
        )
        Tabs(
            tabs = listOf(Tab.ALL_NEWS, Tab.HEADERS),
            currentTab = currentTab,
            onTabSelect = { currentTab = it }
        )
        when (currentTab) {
            Tab.ALL_NEWS -> NewsTabContent(
                onArticle = { navController.navigate(Destination.Article) },
                modifier = Modifier.fillMaxSize(),
            )
            Tab.HEADERS -> HeadersTabContent(
                onArticle = { navController.navigate(Destination.Article) },
                modifier = Modifier.fillMaxSize()
            )
            else -> throw IllegalStateException("Expected ${Tab.ALL_NEWS} or ${Tab.HEADERS}")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NewsScreenPreview() = DVXTheme {
    NewsScreen(
        navController = rememberNavController(),
        modifier = Modifier.fillMaxWidth()
    )
}