package dvx.news.app.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
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
import dvx.news.app.states.SettingsTab
import dvx.news.app.themes.DVXTheme
import dvx.news.app.viewModels.SettingsViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    settingsViewModel: SettingsViewModel = koinViewModel()
) {
    val settings by settingsViewModel.state.collectAsState()

    val composableCoroutine = rememberCoroutineScope()

    val tabs = SettingsTab.entries
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { tabs.size }
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        DVXTopAppBar(
            title = stringResource(R.string.settings),
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
        HorizontalPager(
            state = pagerState,
            verticalAlignment = Alignment.Top,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            when (tabs[page]) {
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
    SettingsScreen(navController = rememberNavController())
}