package dvx.news.app.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dvx.news.app.R
import dvx.news.app.themes.DVXTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DVXTopLogoAppBar(
    modifier: Modifier = Modifier,
    title: String = "",
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                color = Color(0xFF484C56),
                style = MaterialTheme.typography.bodyMedium,
            )
        },
        navigationIcon = {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 10.dp)
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