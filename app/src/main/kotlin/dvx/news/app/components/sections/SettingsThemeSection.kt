package dvx.news.app.components.sections

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dvx.news.app.R
import dvx.news.app.components.SelectableRowItem
import dvx.news.app.states.Theme
import dvx.news.app.states.localizedName
import dvx.news.app.themes.DVXTheme

@Composable
fun SettingsThemeSection(
    currentTheme: Theme,
    onChangeTheme: (Theme) -> Unit,
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
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.64f),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(
                    top = 40.dp,
                    bottom = 22.dp,
                    start = 20.dp,
                    end = 20.dp
                )
                .fillMaxWidth()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Theme.entries.forEach { theme ->
                SelectableRowItem(
                    text = theme.localizedName,
                    selected = theme == currentTheme,
                    onClick = { onChangeTheme(theme) }
                )
                HorizontalDivider(
                    color = MaterialTheme.colorScheme.onSurface
                        .copy(alpha = 0.24f),
                )
            }
        }
    }
}

@Preview
@Composable
private fun SettingsThemeSectionPreview() = DVXTheme {
    SettingsThemeSection(
        currentTheme = Theme.SYSTEM,
        onChangeTheme = {}
    )
}