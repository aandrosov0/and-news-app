package dvx.news.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dvx.news.app.R
import dvx.news.app.states.CategoryScreen
import dvx.news.app.states.CategoryUiState
import dvx.news.app.states.IncludesScreen
import dvx.news.app.states.NewsScreen
import dvx.news.app.states.NewsTab
import dvx.news.app.states.SettingsScreen
import dvx.news.app.states.iconId
import dvx.news.app.ui.components.AppLogo
import dvx.news.app.ui.components.DVXCard
import dvx.news.app.ui.components.HorizontalButton
import kotlin.collections.forEach

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OverviewScreen(
    categories: List<CategoryUiState>,
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController()
) {
    Scaffold(
        modifier = modifier,
        topBar = { OverviewTopBar() },
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    ) { paddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(
                    horizontal = 10.dp,
                    vertical = 20.dp
                )
                .padding(paddings)
        ) {
            Actions(
                onProfile = { },
                onSettings = { navController.navigate(SettingsScreen) },
            )
            Topics(
                onHeadlines = { navController.navigate(NewsScreen(NewsTab.HEADERS)) },
                onNews = { navController.navigate(NewsScreen(NewsTab.ALL_NEWS)) },
                onIncludes = { navController.navigate(IncludesScreen) }
            )
            Categories(
                categories = categories,
                onClick = { navController.navigate(CategoryScreen(it.id, it.name)) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OverviewTopBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = "Mehr",
                style = MaterialTheme.typography.bodyMedium,
            )
        },
        navigationIcon = { AppLogo() }
    )
}

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
    onClick: (CategoryUiState) -> Unit,
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
                    onClick = { onClick(category) },
                    text = category.name,
                    prefixIconId = category.iconId,
                    postfixIconId = R.drawable.ic_down
                )
            }
        }
        HorizontalDivider()
    }
}