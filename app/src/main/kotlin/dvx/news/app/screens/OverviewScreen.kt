package dvx.news.app.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dvx.news.app.components.ActionsOverviewSection
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
        ActionsOverviewSection()
        TopTopicsOverviewSection()
        HeadingsOverviewSection()
    }
}

@Preview(showBackground = true, backgroundColor = 0XFFF)
@Composable
private fun OverviewScreenPreview() = DVXTheme {
    OverviewScreen()
}