package dvx.news.app.components

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
import dvx.news.app.themes.DVXTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DVXTopAppBar(
    parent: String,
    destination: String,
    navController: NavController,
    modifier: Modifier = Modifier,
    isShowingBack: Boolean = true,
    isShowingLogo: Boolean = false,
    isShowingShare: Boolean = true,
    onExport: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = destination,
                style = MaterialTheme.typography.bodyMedium,
            )
        },
        navigationIcon = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (isShowingLogo) {
                    AppLogo()
                }

                if (isShowingBack) {
                    HorizontalButton(
                        onClick = navController::navigateUp,
                        icon = R.drawable.ic_arrow_left,
                        label = parent
                    )
                }
            }
        },
        actions = {
            if (isShowingShare) {
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
        parent = "Lifestyle",
        destination = "Mehr",
        navController = rememberNavController()
    )
}