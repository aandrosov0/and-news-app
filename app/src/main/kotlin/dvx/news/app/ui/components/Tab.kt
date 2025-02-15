package dvx.news.app.ui.components

import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import dvx.news.app.ui.themes.DVXTheme

@Composable
fun Tab(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    androidx.compose.material3.Tab(
        selected = selected,
        onClick = onClick,
        text = {
            Text(
                text = text,
                color = LocalContentColor.current,
                style = MaterialTheme.typography.titleMedium,
                modifier = modifier.alpha(if (selected) 1f else .64f)
            )
        }
    )
}

@Preview
@Composable
private fun TabPreview() = DVXTheme {
    Tab(
        text = "Text",
        selected = true,
        onClick = {}
    )
}