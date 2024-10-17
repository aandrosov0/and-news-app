package dvx.news.app.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dvx.news.app.R
import dvx.news.app.components.DVXTopAppBar
import dvx.news.app.components.sections.SettingsMessagesSection
import dvx.news.app.components.sections.SettingsThemeSection
import dvx.news.app.components.Tabs
import dvx.news.app.states.Tab
import dvx.news.app.themes.DVXTheme
import dvx.news.app.viewModels.SettingsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreenHeader(
    currentTab: Tab,
    onTabSelect: (Tab) -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        DVXTopAppBar(
            title = stringResource(R.string.settings),
            destination = stringResource(R.string.menu),
            onBack = onNavigateUp
        )
        Tabs(
            tabs = listOf(Tab.REPRESENTATION, Tab.MESSAGES),
            currentTab = currentTab,
            onTabSelect = onTabSelect
        )
    }
}

@Composable
fun SettingsScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    settingsViewModel: SettingsViewModel = koinViewModel()
) {
    var currentTab by remember { mutableStateOf(Tab.REPRESENTATION) }
    val settings by settingsViewModel.state.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        SettingsScreenHeader(
            currentTab = currentTab,
            onTabSelect = { currentTab = it },
            onNavigateUp = navController::navigateUp
        )
        when (currentTab) {
            Tab.REPRESENTATION -> SettingsThemeSection(
                currentTheme = settings.theme,
                onThemeChange = { settingsViewModel.update(settings.copy(theme = it)) }
            )
            Tab.MESSAGES -> SettingsMessagesSection()
            else -> throw IllegalStateException("Expected ${Tab.REPRESENTATION} or ${Tab.MESSAGES}")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingsScreenPreview() = DVXTheme {
    SettingsScreen(navController = rememberNavController())
}