package dvx.news.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dvx.news.app.appModule
import dvx.news.app.states.Destination
import dvx.news.app.states.NewsTab
import dvx.news.app.themes.DVXTheme
import dvx.news.data.dataOfflineModule
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinApplication
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import dvx.news.app.R
import dvx.news.app.ui.components.DVXCard
import dvx.news.app.states.CategoryUiState
import dvx.news.app.states.iconId
import dvx.news.app.ui.components.HorizontalButton
import dvx.news.app.viewModels.OverviewViewModel
import kotlin.collections.forEach

@Composable
private fun Actions(
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

@Composable
private fun Topics(
    onHeadlines: () -> Unit,
    onNews: () -> Unit,
    onIncludes: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(top = 28.dp)
    ) {
        Text(
            text = "TOP-THEMEN",
            fontSize = 26.sp,
            modifier = Modifier.padding(
                start = 10.dp,
                end = 10.dp,
                bottom = 8.dp
            )
        )
        HorizontalDivider()
        HorizontalButton(
            onClick = onHeadlines,
            text = "Schlagzeilen",
            prefixIconId = R.drawable.ic_schlagzeilen,
        )
        HorizontalDivider()
        HorizontalButton(
            onClick = onNews,
            text = "Newsticker",
            prefixIconId = R.drawable.ic_schlagzeilen,
        )
        HorizontalDivider()
        HorizontalButton(
            onClick = onIncludes,
            text = "Includes",
            prefixIconId = R.drawable.ic_schlagzeilen,
        )
        HorizontalDivider()
    }
}

@Composable
private fun Categories(
    categories: List<CategoryUiState>,
    onClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.topics),
            fontSize = 26.sp,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(
                start = 10.dp,
                end = 10.dp,
                bottom = 8.dp,
                top = 18.dp
            )
        )
        Column {
            categories.forEach { category ->
                HorizontalDivider()
                HorizontalButton(
                    onClick = { onClick(category.id) },
                    text = category.name,
                    prefixIconId = category.iconId,
                    postfixIconId = R.drawable.ic_down
                )
            }
        }
        HorizontalDivider()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OverviewScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    overviewViewModel: OverviewViewModel = koinViewModel()
) {
    LaunchedEffect(Unit) { overviewViewModel.getAll() }
    val uiState by overviewViewModel.uiState.collectAsState()

    PullToRefreshBox(
        isRefreshing = uiState.isLoading,
        onRefresh = { overviewViewModel.getAll(refresh = true) },
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(
                    horizontal = 10.dp,
                    vertical = 20.dp
                )
        ) {
            Actions(
                onProfile = { },
                onSettings = { navController.navigate(Destination.Settings) },
            )
            Topics(
                onHeadlines = { navController.navigate(Destination.News(NewsTab.HEADERS)) },
                onNews = { navController.navigate(Destination.News(NewsTab.ALL_NEWS)) },
                onIncludes = { navController.navigate(Destination.Includes) }
            )
            Categories(
                categories = uiState.categories,
                onClick = {}
            )
        }
    }
}


@Preview
@Composable
private fun OverviewScreenPreview() = DVXTheme {
    KoinApplication(application = { modules(dataOfflineModule, appModule) }) {
        OverviewScreen(
            navController = rememberNavController()
        )
    }
}