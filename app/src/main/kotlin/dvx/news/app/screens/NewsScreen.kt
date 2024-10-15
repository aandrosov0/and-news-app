package dvx.news.app.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import dvx.news.app.states.Tab
import dvx.news.app.themes.DVXTheme

@Composable
fun NewsScreenHeader(
    currentTab: Tab,
    onTabSelect: (Tab) -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        DVXTopAppBar(
            title = "",
            destination = stringResource(R.string.menu),
            onNavigateUp = onNavigateUp
        )
        Tabs(
            tabs = listOf(Tab.ALL_NEWS, Tab.HEADERS),
            currentTab = currentTab,
            onTabSelect = onTabSelect,
        )
    }
}

@Composable
fun NewsScreenContent(
    currentTab: Tab,
    onNavigateTo: (Destination) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        when (currentTab) {
            Tab.ALL_NEWS -> NewsAllSection(onNews = { onNavigateTo(Destination.Article) })
            Tab.HEADERS -> NewsHeadlinesSection(onNews = { onNavigateTo(Destination.Article) })
            else -> throw IllegalStateException("Expected ${Tab.ALL_NEWS} or ${Tab.HEADERS}")
        }
    }
}

@Composable
fun NewsScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var currentTab by remember { mutableStateOf(Tab.ALL_NEWS) }

    Column(modifier = modifier) {
        NewsScreenHeader(
            currentTab = currentTab,
            onTabSelect = { currentTab = it },
            onNavigateUp = navController::navigateUp
        )
        NewsScreenContent(
            currentTab = currentTab,
            onNavigateTo = { navController.navigate(it) },
            modifier = Modifier
                .padding(horizontal = 20.dp)
        )
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