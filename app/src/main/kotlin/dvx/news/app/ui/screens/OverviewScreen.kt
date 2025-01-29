package dvx.news.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dvx.news.app.R
import dvx.news.app.ui.components.DVXCard
import dvx.news.app.ui.components.DVXTopLogoAppBar
import dvx.news.app.ui.screens.navigation.Category
import dvx.news.app.ui.screens.navigation.Includes
import dvx.news.app.ui.screens.navigation.News
import dvx.news.app.ui.screens.navigation.Settings
import dvx.news.app.ui.states.CategoryUiState
import dvx.news.app.ui.states.NewsTab
import dvx.news.app.ui.states.iconId
import dvx.news.app.ui.themes.DVXTheme
import dvx.news.app.ui.viewModels.MainViewModel
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun OverviewScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = koinInject(),
) {
    val uiState by mainViewModel.uiState.collectAsState()
    Scaffold(
        modifier = modifier,
        topBar = { DVXTopLogoAppBar(title = stringResource(R.string.menu)) },
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
                onSettings = { navController.navigate(Settings) },
            )
            Topics(
                onHeadlines = { navController.navigate(News(NewsTab.HEADERS)) },
                onNews = { navController.navigate(News(NewsTab.ALL_NEWS)) },
                onIncludes = { navController.navigate(Includes) }
            )
            Categories(
                categories = uiState.categories,
                onClick = { navController.navigate(Category(it.id, it.name)) }
            )
        }
    }
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

@Composable
private fun HorizontalButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    prefixIconId: Int? = null,
    postfixIconId: Int? = null,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.primary
        ),
        shape = RectangleShape,
        contentPadding = PaddingValues(
            horizontal = 20.dp,
            vertical = 16.dp
        ),
        modifier = Modifier
    ) {
        if (prefixIconId != null) {
            Icon(
                painter = painterResource(prefixIconId),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .size(32.dp)
            )
        }
        Text(
            text = text,
            fontSize = 18.sp,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .padding(start = 20.dp)
                .alpha(.64f)
        )
        Spacer(
            modifier
                .weight(1f)
        )
        if (postfixIconId != null) {
            Icon(
                painter = painterResource(postfixIconId),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .size(16.dp)
            )
        }
    }
}

@Preview
@Composable
private fun HorizontalButtonPreview() = DVXTheme {
    HorizontalButton(
        onClick = {},
        text = "Sport",
        prefixIconId = R.drawable.ic_sport,
        postfixIconId = R.drawable.ic_down,
    )
}