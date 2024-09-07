package dvx.news.app.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dvx.news.app.themes.DVXTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DVXTopAppBar(
    title: String,
    destination: String,
    modifier: Modifier = Modifier
) {
    val elementsColor = Color(0xFF484C56)
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = title,
                color = elementsColor,
                style = MaterialTheme.typography.bodyMedium,
            )
        },
        navigationIcon = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                    contentDescription = null,
                    tint = elementsColor
                )
                Text(
                    text = destination,
                    style = MaterialTheme.typography.bodyMedium,
                    color = elementsColor,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    )
}

@Preview
@Composable
private fun DVXTopAppBarPreview() = DVXTheme {
    DVXTopAppBar(
        title = "Lifestyle",
        destination = "Mehr"
    )
}