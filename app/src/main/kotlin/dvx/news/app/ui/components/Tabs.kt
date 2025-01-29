package dvx.news.app.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import dvx.news.app.ui.states.SettingsTab
import dvx.news.app.ui.states.Tab
import dvx.news.app.ui.themes.DVXTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T : Tab> Tabs(
    tabs: List<T>,
    currentTab: T,
    onTabSelect: (T) -> Unit,
    modifier: Modifier = Modifier
) {
    PrimaryTabRow(
        selectedTabIndex = 1,
        indicator = {
            TabRowDefaults.PrimaryIndicator(
                modifier = Modifier.tabIndicatorOffset(
                    selectedTabIndex = tabs.indexOf(currentTab),
                    matchContentSize = false
                ),
                width = Dp.Unspecified,
                shape = RectangleShape
            )
        },
        modifier = modifier
    ) {
        tabs.forEach { tab ->
            Tab(
                selected = tab == currentTab,
                onClick = { onTabSelect(tab) },
                text = {
                    Text(
                        text = tab.localizedName,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = modifier
                            .alpha(if (tab == currentTab) 1f else .64f)
                    )
                }
            )
        }
    }
}

@Preview
@Composable
private fun NewsTabsPreview() = DVXTheme {
    var currentSettingsTab by remember { mutableStateOf(SettingsTab.MESSAGES) }
    Tabs(
        tabs = listOf(SettingsTab.MESSAGES, SettingsTab.REPRESENTATION),
        currentTab = currentSettingsTab,
        onTabSelect = { currentSettingsTab = it }
    )
}