package dvx.news.app.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dvx.news.app.R
import dvx.news.app.components.DVXCard
import dvx.news.app.components.HeadingsOverviewSection
import dvx.news.app.components.TopTopicsOverviewSection
import dvx.news.app.themes.DVXTheme

@Composable
fun OverviewScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            DVXCard(
                iconId = R.drawable.ic_profile,
                text = "Mein Konto",
                modifier = Modifier
                    .weight(0.5f)
            )
            DVXCard(
                iconId = R.drawable.ic_settings,
                text = "Einstellungen",
                modifier = Modifier
                    .weight(0.5f)
            )
        }
        TopTopicsOverviewSection(
            modifier = Modifier
                .padding(top = 28.dp)
        )
        HeadingsOverviewSection(
            modifier = Modifier
                .padding(top = 18.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0XFFF)
@Composable
private fun OverviewScreenPreview() = DVXTheme {
    OverviewScreen()
}