package dvx.news.app.components

import androidx.compose.foundation.Image
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import dvx.news.app.R
import dvx.news.app.themes.DVXTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DVXTopLogoAppBar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.logo),
                contentDescription = null
            )
        },
        modifier = modifier
    )
}

@Preview
@Composable
private fun DVXTopLogoAppBarPreview() = DVXTheme {
    DVXTopLogoAppBar()
}