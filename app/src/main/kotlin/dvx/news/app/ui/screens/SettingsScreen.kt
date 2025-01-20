package dvx.news.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import androidx.navigation.compose.rememberNavController
import dvx.news.app.R
import dvx.news.app.states.SettingsTab
import dvx.news.app.states.SettingsUiState
import dvx.news.app.states.Theme
import dvx.news.app.states.localizedName
import dvx.news.app.ui.components.BackButton
import dvx.news.app.ui.components.HorizontalTabPager
import dvx.news.app.ui.components.NotificationAlertDialog
import dvx.news.app.ui.components.SelectableRowItem

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsTopBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = "Einstellungen",
                style = MaterialTheme.typography.bodyMedium,
            )
        },
        navigationIcon = {
            BackButton(
                onClick = onBackClick,
                icon = R.drawable.ic_arrow_left,
                label = "Mehr"
            )
        },
        windowInsets = TopAppBarDefaults.windowInsets.exclude(WindowInsets.statusBars)
    )
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
    settings: SettingsUiState,
    onUpdateSettings: (SettingsUiState) -> Unit,
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController()
) {
    Scaffold(
        modifier = modifier,
        topBar = { SettingsTopBar(onBackClick = navController::navigateUp) },
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
                        currentTheme = settings.theme,
                        onThemeChange = { onUpdateSettings(settings.copy(theme = it)) }
                    )
                    SettingsTab.MESSAGES -> Messages()
                }
            }
        }
    }
}