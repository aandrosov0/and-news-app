package com.dvx.news.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dvx.news.ui.components.DVXTopLogoAppBar
import com.dvx.news.ui.screens.navigation.Category
import com.dvx.news.ui.screens.navigation.News
import com.dvx.news.ui.screens.navigation.Settings
import com.dvx.news.ui.states.CategoryUiState
import com.dvx.news.R
import com.dvx.news.ui.states.NewsTab
import com.dvx.news.ui.states.iconResId
import com.dvx.news.ui.themes.DVXTheme
import com.dvx.news.ui.viewModels.MainViewModel
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun OverviewScreen(
    onNavigateScreen: (Any) -> Unit,
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = koinInject(),
) {
    val uiState by mainViewModel.uiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { DVXTopLogoAppBar(scrollBehavior = scrollBehavior) },
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    ) { paddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 10.dp, vertical = 20.dp)
                .padding(paddings)
        ) {
            Actions(
                onProfileClick = { },
                onSettingsClick = { onNavigateScreen(Settings) },
            )
            Topics(
                onHeadlines = { onNavigateScreen(News(NewsTab.HEADERS)) },
                onNews = { onNavigateScreen(News(NewsTab.ALL_NEWS)) },
            )
            Categories(
                categories = uiState.categories,
                onCategoryClick = { onNavigateScreen(Category(it.id, it.name)) }
            )
        }
    }
}

@Composable
private fun Actions(
    onProfileClick: () -> Unit,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .height(86.dp)
            .fillMaxWidth()
    ) {
        ActionButton(
            icon = painterResource(R.drawable.ic_profile),
            label = stringResource(R.string.my_account),
            onClick = onProfileClick,
            modifier = Modifier.weight(.5f)
        )
        ActionButton(
            icon = painterResource(R.drawable.ic_settings),
            label = stringResource(R.string.settings),
            onClick = onSettingsClick,
            modifier = Modifier.weight(.5f)
        )
    }
}

@Composable
private fun Topics(
    onHeadlines: () -> Unit,
    onNews: () -> Unit,
    modifier: Modifier = Modifier
) {
    val topics = listOf(
        R.string.headlines,
        R.string.news_ticker,
    )
    Column(modifier = modifier.padding(top = 28.dp)) {
        Title(text = stringResource(R.string.top_topics))
        topics.forEach { topic ->
            HorizontalDivider()
            DropdownButton(
                icon = painterResource(R.drawable.ic_news),
                label = stringResource(topic),
                onClick = {
                    when (topic) {
                        R.string.headlines -> onHeadlines()
                        R.string.news_ticker -> onNews()
                    }
                },
                expandable = false
            )
        }
        HorizontalDivider()
    }
}

@Composable
private fun Categories(
    categories: List<CategoryUiState>,
    onCategoryClick: (CategoryUiState) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(top = 18.dp)) {
        Title(text = stringResource(R.string.topics))
        categories.forEach { category ->
            HorizontalDivider()
            DropdownButton(
                icon = painterResource(category.iconResId),
                label = category.name,
                onClick = { onCategoryClick(category) }
            )
        }
        HorizontalDivider()
    }
}

@Composable
private fun Title(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier.padding(
            start = 10.dp, end = 10.dp,
            bottom = 8.dp
        ),
        fontSize = 26.sp,
        color = MaterialTheme.colorScheme.onSurface
    )
}

@Composable
private fun DropdownButton(
    icon: Painter,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    expanded: Boolean = false,
    expandable: Boolean = true,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    contentPadding: PaddingValues = PaddingValues(horizontal = 20.dp, vertical = 16.dp)
) {
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(contentPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CompositionLocalProvider(LocalContentColor provides contentColor) {
            Icon(
                painter = icon,
                contentDescription = label,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = label,
                fontSize = 18.sp,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.alpha(.64f)
            )
            Spacer(modifier = Modifier.weight(1f))
            if (expandable) {
                Icon(
                    painter = painterResource(R.drawable.ic_down),
                    contentDescription = stringResource(R.string.dropdown),
                    modifier = Modifier
                        .size(16.dp)
                        .rotate(if (expanded) 180f else 0f)
                )
            }
        }
    }
}

@Composable
private fun ActionButton(
    icon: Painter,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(containerColor)
) {
    val containerShape = RoundedCornerShape(12.dp)
    Column(
        modifier = modifier
            .size(width = 271.dp, height = 104.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(.16f),
                shape = containerShape
            )
            .clip(containerShape)
            .background(containerColor)
            .clickable(onClick = onClick),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CompositionLocalProvider(LocalContentColor provides contentColor) {
            Icon(
                painter = icon,
                contentDescription = label,
                modifier = Modifier.size(32.dp)
            )
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Preview
@Composable
private fun TitlePreview() = DVXTheme {
    Title(text = "TOP-THEMEN")
}

@Preview
@Composable
private fun ActionButtonPreview() = DVXTheme {
    ActionButton(
        icon = painterResource(R.drawable.ic_profile),
        label = "Mein Konto",
        onClick = {}
    )
}

@Preview
@Composable
private fun DropdownButtonPreview() = DVXTheme {
    DropdownButton(
        icon = painterResource(R.drawable.ic_sport),
        label = stringResource(R.string.sport),
        onClick = {}
    )
}
