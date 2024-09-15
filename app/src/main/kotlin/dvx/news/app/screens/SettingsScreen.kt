package dvx.news.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dvx.news.app.components.SettingsMessagesSection
import dvx.news.app.components.SettingsThemeSection
import dvx.news.app.components.Tabs
import dvx.news.app.states.Tab
import dvx.news.app.themes.DVXTheme

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    var currentTab by remember { mutableStateOf(Tab.REPRESENTATION) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Tabs(
            tabs = listOf(Tab.REPRESENTATION, Tab.MESSAGES),
            currentTab = currentTab,
            onTabSelect = { currentTab = it }
        )
        when (currentTab) {
            Tab.REPRESENTATION -> SettingsThemeSection()
            Tab.MESSAGES -> SettingsMessagesSection()
            else -> throw IllegalStateException("Expected ${Tab.REPRESENTATION} or ${Tab.MESSAGES}")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingsScreenPreview() = DVXTheme {
    SettingsScreen()
}