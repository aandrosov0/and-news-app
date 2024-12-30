package dvx.news.app.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dvx.news.app.R
import dvx.news.app.states.TopAppBarDefaults
import dvx.news.app.states.TopAppBarParameters
import dvx.news.app.themes.DVXTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DVXTopAppBar(
    navController: NavController,
    topAppBarParameters: TopAppBarParameters,
    modifier: Modifier = Modifier,
    onExport: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = topAppBarParameters.destination,
                style = MaterialTheme.typography.bodyMedium,
            )
        },
        navigationIcon = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (topAppBarParameters.isShowingLogo) {
                    AppLogo()
                }

                if (topAppBarParameters.isShowingBack) {
                    BackButton(
                        onClick = navController::navigateUp,
                        icon = R.drawable.ic_arrow_left,
                        label = topAppBarParameters.parent
                    )
                }
            }
        },
        actions = {
            if (topAppBarParameters.isShowingShare) {
                IconButton(onClick = onExport) {
                    Icon(
                        painter = painterResource(R.drawable.ic_export),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        },
    )
}

@Preview
@Composable
private fun DVXTopAppBarPreview() = DVXTheme {
    DVXTopAppBar(
        navController = rememberNavController(),
        topAppBarParameters = TopAppBarDefaults.topAppBarParameters
    )
}