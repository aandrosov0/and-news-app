package dvx.news.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dvx.news.app.R
import dvx.news.app.ui.components.HorizontalTabPager
import dvx.news.app.ui.components.NotificationAlertDialog
import dvx.news.app.ui.components.SelectableRowItem
import dvx.news.app.states.SettingsTab
import dvx.news.app.states.Theme
import dvx.news.app.states.localizedName
import dvx.news.app.themes.DVXTheme
import dvx.news.app.viewModels.SettingsViewModel
import org.koin.androidx.compose.koinViewModel

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
                SettingsTab.REPRESENTATION -> Themes(
                    currentTheme = settings.theme,
                    onThemeChange = { settingsViewModel.update(settings.copy(theme = it)) }
                )
                SettingsTab.MESSAGES -> Messages()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingsScreenPreview() = DVXTheme {
    SettingsScreen()
}