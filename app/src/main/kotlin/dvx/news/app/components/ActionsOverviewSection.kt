package dvx.news.app.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dvx.news.app.R
import dvx.news.app.themes.DVXTheme

@Composable
fun ActionsOverviewSection(
    onProfile: () -> Unit,
    onSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .height(86.dp)
            .fillMaxWidth()
    ) {
        DVXCard(
            iconId = R.drawable.ic_profile,
            text = stringResource(R.string.my_account),
            onClick = onProfile,
            modifier = Modifier
                .weight(0.5f)
        )
        DVXCard(
            iconId = R.drawable.ic_settings,
            text = stringResource(R.string.settings),
            onClick = onSettings,
            modifier = Modifier
                .weight(0.5f)
        )
    }
}

@Preview
@Composable
private fun ActionsOverviewSectionPreview() = DVXTheme {
    ActionsOverviewSection(
        onProfile = {},
        onSettings = {},
    )
}