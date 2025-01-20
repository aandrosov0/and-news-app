package dvx.news.app.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring.StiffnessHigh
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dvx.news.app.R
import dvx.news.app.states.DefaultScreenState
import dvx.news.app.states.ErrorUiState
import dvx.news.app.states.RefreshableScreenState
import dvx.news.app.states.ScreenState
import dvx.news.app.themes.DVXTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen(
    modifier: Modifier = Modifier,
    state: ScreenState = DefaultScreenState(),
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = contentColorFor(containerColor),
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    content: @Composable () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        snackbarHost = snackbarHost,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        containerColor = containerColor,
        contentColor = contentColor,
        contentWindowInsets = contentWindowInsets,
    ) { paddingValues ->
        val wrapperModifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)

        when (state) {
            is DefaultScreenState ->
                Box(modifier = wrapperModifier) {
                    ScreenContent(
                        error = state.error,
                        content = content,
                        onAction = state.onRefresh,
                    )
                }

            is RefreshableScreenState -> {
                val pullToRefreshState = rememberPullToRefreshState()

                Column(
                    modifier = wrapperModifier.pullToRefresh(
                        isRefreshing = state.isRefreshing,
                        state = pullToRefreshState,
                        onRefresh = state.onRefresh
                    )
                ) {
                    ScreenProgressIndicator(
                        modifier = Modifier
                            .animateContentSize(spring(stiffness = StiffnessHigh))
                            .height(80.dp * pullToRefreshState.distanceFraction)
                    )
                    ScreenContent(
                        error = state.error,
                        content = content,
                        onAction = state.onRefresh
                    )
                }
            }
        }
    }
}

@Composable
private fun ScreenContent(
    error: ErrorUiState? = null,
    onAction: () -> Unit,
    content: @Composable () -> Unit = {},
) {
    if (error != null) {
        ErrorBox(
            icon = painterResource(error.iconId),
            error = stringResource(error.messageId),
            action = stringResource(error.actionId),
            onActionClick = onAction,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        )
    } else {
        content()
    }
}

@Composable
private fun ScreenProgressIndicator(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            )
        )
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface.copy(alpha = .5f))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_logo),
            contentDescription = null,
            modifier = Modifier
                .size(94.dp)
                .rotate(rotation),
            tint = Color.Unspecified,
        )
        Text(
            text = stringResource(R.string.news),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 44.sp,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Preview
@Composable
private fun ScreenProgressIndicatorPreview() {
    DVXTheme {
        ScreenProgressIndicator(modifier = Modifier.fillMaxSize())
    }
}