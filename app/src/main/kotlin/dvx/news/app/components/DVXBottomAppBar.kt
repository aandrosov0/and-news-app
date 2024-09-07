package dvx.news.app.components

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dvx.news.app.themes.DVXTheme

@Composable
fun DVXBottomAppBar(modifier: Modifier = Modifier) {
    BottomAppBar(modifier = modifier) {
        IconButton(onClick = {}) {
            Icon()
        }
    }
}

@Preview
@Composable
private fun DVXBottomAppBarPreview() = DVXTheme {
    DVXBottomAppBar()
}