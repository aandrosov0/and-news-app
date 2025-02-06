package dvx.news.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dvx.news.app.R
import dvx.news.app.ui.components.DVXTopAppBar
import dvx.news.app.ui.components.HorizontalTabPager
import dvx.news.app.ui.components.NotificationAlertDialog
import dvx.news.app.ui.states.SettingsTab
import dvx.news.app.ui.states.ThemeUiState
import dvx.news.app.ui.states.localizedName
import dvx.news.app.ui.themes.DVXTheme
import dvx.news.app.ui.viewModels.MainViewModel
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SettingsScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = koinInject(),
) {
    val uiState by mainViewModel.uiState.collectAsState()
    val settings = uiState.settings
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
        HorizontalTabPager(
            tabs = SettingsTab.entries,
            initialTab = SettingsTab.REPRESENTATION,
            modifier = Modifier.padding(paddings)
        ) { tab ->
            when (tab) {
                SettingsTab.REPRESENTATION -> Representation(
                    currentTheme = uiState.settings.theme,
                    onThemeSelect = {
                        val newSettings = settings.copy(theme = it)
                        uiState.onSettingsChange(newSettings)
                    }
                )
                SettingsTab.MESSAGES -> Messages()
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
private fun Representation(
    currentTheme: ThemeUiState,
    onThemeSelect: (ThemeUiState) -> Unit,
    modifier: Modifier = Modifier
) {
    val dividerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = .24f)
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(text = stringResource(R.string.settings1_main))
        ThemeUiState.entries.forEach { theme ->
            SelectableItem(
                label = theme.localizedName,
                selected = theme == currentTheme,
                onSelect = { onThemeSelect(theme) }
            )
            HorizontalDivider(color = dividerColor)
        }
    }
}

@Composable
private fun Title(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier
            .padding(
                top = 40.dp, bottom = 22.dp,
                start = 20.dp, end = 20.dp
            )
            .alpha(0.64f),
        fontSize = 16.sp,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
private fun SelectableItem(
    label: String,
    selected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable(onClick = onSelect)
            .padding(horizontal = 10.dp, vertical = 20.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            modifier = Modifier.alpha(.64f),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodySmall
        )
        if (selected) {
            Image(
                painter = painterResource(R.drawable.ic_tick),
                contentDescription = stringResource(R.string.tick),
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Preview
@Composable
private fun RepresentationPreview() = DVXTheme {
    Representation(
        currentTheme = ThemeUiState.SYSTEM,
        onThemeSelect = {}
    )
}

@Preview
@Composable
private fun TitlePreview() = DVXTheme {
    Title(text = stringResource(R.string.settings1_main))
}

@Preview
@Composable
private fun SelectableItemPreview() = DVXTheme {
    SelectableItem(
        label = "Schlagzailen",
        selected = true,
        onSelect = {}
    )
}