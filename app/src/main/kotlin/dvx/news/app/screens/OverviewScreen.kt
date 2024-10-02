package dvx.news.app.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dvx.news.app.R
import dvx.news.app.components.ActionsOverviewSection
import dvx.news.app.components.DVXTopLogoAppBar
import dvx.news.app.components.HeadingsOverviewSection
import dvx.news.app.components.TopTopicsOverviewSection
import dvx.news.app.states.Destination
import dvx.news.app.themes.DVXTheme

@Composable
fun OverviewScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) = Column(modifier = modifier) {
    DVXTopLogoAppBar(title = stringResource(R.string.menu))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 10.dp,
                vertical = 20.dp
            )
    ) {
        ActionsOverviewSection(
            onProfile = { },
            onSettings = { navController.navigate(Destination.Settings) },
        )
        TopTopicsOverviewSection(
            onNews = { navController.navigate(Destination.News) },
            onIncludes = { navController.navigate(Destination.Includes) }
        )
        HeadingsOverviewSection()
    }
}


@Preview(showBackground = true, backgroundColor = 0XFFF)
@Composable
private fun OverviewScreenPreview() = DVXTheme {
    OverviewScreen(
        navController = rememberNavController()
    )
}