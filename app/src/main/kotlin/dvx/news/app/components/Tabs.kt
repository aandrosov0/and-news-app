package dvx.news.app.components

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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import dvx.news.app.states.Tab
import dvx.news.app.states.localizedName
import dvx.news.app.themes.DVXTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tabs(
    tabs: List<Tab>,
    currentTab: Tab,
    onTabSelect: (Tab) -> Unit,
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
                    val alpha = if (tab == currentTab) 1f else 0.64f
                    Text(
                        text = tab.localizedName,
                        color = MaterialTheme.colorScheme.onSurface
                            .copy(alpha = alpha),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            )
        }
    }
}

@Preview
@Composable
private fun NewsTabsPreview() = DVXTheme {
    var currentTab by remember { mutableStateOf(Tab.ALL_NEWS) }
    Tabs(
        tabs = listOf(Tab.ALL_NEWS, Tab.HEADERS),
        currentTab = currentTab,
        onTabSelect = { currentTab = it }
    )
}