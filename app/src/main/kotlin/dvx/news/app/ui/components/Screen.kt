package dvx.news.app.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import dvx.news.app.states.DefaultScreenState
import dvx.news.app.states.ErrorUiState
import dvx.news.app.states.RefreshableScreenState
import dvx.news.app.states.ScreenState

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
    content: @Composable BoxScope.() -> Unit,
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
            is RefreshableScreenState ->
                PullToRefreshBox(
                    isRefreshing = state.isRefreshing,
                    onRefresh = state.onRefresh,
                    modifier = wrapperModifier
                ) {
                    ScreenContent(
                        error = state.error,
                        content = content,
                        onAction = state.onRefresh
                    )
                }
        }
    }
}

@Composable
private fun BoxScope.ScreenContent(
    error: ErrorUiState? = null,
    onAction: () -> Unit,
    content: @Composable BoxScope.() -> Unit = {},
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