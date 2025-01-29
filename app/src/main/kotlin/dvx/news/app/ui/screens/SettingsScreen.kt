package dvx.news.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dvx.news.app.R
import dvx.news.app.ui.components.DVXTopAppBar
import dvx.news.app.ui.components.HorizontalTabPager
import dvx.news.app.ui.components.NotificationAlertDialog
import dvx.news.app.ui.components.SelectableRowItem
import dvx.news.app.ui.states.SettingsTab
import dvx.news.app.ui.states.Theme
import dvx.news.app.ui.states.localizedName
import dvx.news.app.ui.viewModels.MainViewModel
import org.koin.compose.koinInject

@Composable
private fun Themes(
    currentTheme: Theme,
    onThemeChange: (Theme) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.settings1_main),
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(
                    top = 40.dp,
                    bottom = 22.dp,
                    start = 20.dp,
                    end = 20.dp
                )
                .fillMaxWidth()
                .alpha(0.64f)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Theme.entries.forEach { theme ->
                SelectableRowItem(
                    text = theme.localizedName,
                    selected = theme == currentTheme,
                    onClick = { onThemeChange(theme) }
                )
                HorizontalDivider(
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .alpha(0.24f)
                )
            }
        }
    }
}

@Composable
private fun Messages() {
    Column(modifier = Modifier.fillMaxSize()) {
        var isOpenedAlertDialog by remember { mutableStateOf(true) }
        when {
            isOpenedAlertDialog -> NotificationAlertDialog(
                title = stringResource(R.string.eligibility_title),
                text = stringResource(R.string.eligibility_text),
                onConfirm = { isOpenedAlertDialog = false },
                onDismiss = { isOpenedAlertDialog = false },
            )
        }
    }
}

@Composable
internal fun SettingsScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = koinInject(),
) {
    val uiState by mainViewModel.uiState.collectAsState()
    Scaffold(
        modifier = modifier,
        topBar = {
            DVXTopAppBar(
                title = stringResource(R.string.settings),
                onBackClick = navController::navigateUp
            )
        },
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    ) { paddings ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(paddings)
        ) {
            HorizontalTabPager(
                tabs = SettingsTab.entries,
                initialTab = SettingsTab.REPRESENTATION
            ) { tab ->
                when (tab) {
                    SettingsTab.REPRESENTATION -> Themes(
                        currentTheme = uiState.settings.theme,
                        onThemeChange = { uiState.onSettingsChange(uiState.settings.copy(theme = it)) }
                    )
                    SettingsTab.MESSAGES -> Messages()
                }
            }
        }
    }
}