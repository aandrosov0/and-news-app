package dvx.news.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dvx.news.app.components.HorizontalTabPager
import dvx.news.app.components.sections.SettingsMessagesSection
import dvx.news.app.components.sections.SettingsThemeSection
import dvx.news.app.states.SettingsTab
import dvx.news.app.themes.DVXTheme
import dvx.news.app.viewModels.SettingsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    settingsViewModel: SettingsViewModel = koinViewModel()
) {
    val settings by settingsViewModel.state.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        HorizontalTabPager(
            tabs = SettingsTab.entries,
            initialTab = SettingsTab.REPRESENTATION
        ) { tab ->
            when (tab) {
                SettingsTab.REPRESENTATION -> SettingsThemeSection(
                    currentTheme = settings.theme,
                    onThemeChange = { settingsViewModel.update(settings.copy(theme = it)) }
                )
                SettingsTab.MESSAGES -> SettingsMessagesSection()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingsScreenPreview() = DVXTheme {
    SettingsScreen()
}