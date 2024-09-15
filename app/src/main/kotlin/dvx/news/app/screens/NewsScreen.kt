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
import androidx.compose.ui.tooling.preview.Preview
import dvx.news.app.components.HeadersTabContent
import dvx.news.app.components.NewsTabContent
import dvx.news.app.components.Tabs
import dvx.news.app.states.Tab
import dvx.news.app.themes.DVXTheme

@Composable
fun NewsScreen(modifier: Modifier = Modifier) {
    var currentTab by remember { mutableStateOf(Tab.ALL_NEWS) }

    Column(modifier = modifier
    ) {
        Tabs(
            tabs = listOf(Tab.ALL_NEWS, Tab.HEADERS),
            currentTab = currentTab,
            onTabSelect = { currentTab = it }
        )
        when (currentTab) {
            Tab.ALL_NEWS -> NewsTabContent(modifier = Modifier.fillMaxSize())
            Tab.HEADERS -> HeadersTabContent(modifier = Modifier.fillMaxSize())
            else -> throw IllegalStateException("Expected ${Tab.ALL_NEWS} or ${Tab.HEADERS}")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NewsScreenPreview() = DVXTheme {
    NewsScreen(modifier = Modifier.fillMaxWidth())
}