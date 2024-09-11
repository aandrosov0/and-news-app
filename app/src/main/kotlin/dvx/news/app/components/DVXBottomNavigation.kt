package dvx.news.app.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dvx.news.app.R
import dvx.news.app.themes.DVXTheme

@Composable
fun DVXBottomNavigation(
    modifier: Modifier = Modifier,
    current: String = "Starseite",
) {
    val destinations = listOf(
        "Starseite" to R.drawable.ic_startseite,
        "Sport" to R.drawable.ic_sport,
        "Lifestyle" to R.drawable.ic_lifestyle,
        "Unterhaltung" to R.drawable.ic_unterhaltung,
        "Mehr" to R.drawable.ic_mehr,
    )

    NavigationBar(
        containerColor = Color.White,
        modifier = modifier
    ) {
        for (destination in destinations) {
            val selected = destination.first == current
            val color = if (selected) Color.Unspecified else Color(0xff18191C).copy(alpha = 0.6f)
            NavigationBarItem(
                selected = selected,
                onClick = {},
                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(destination.second),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.size(28.dp)
                        )
                        Text(
                            text = destination.first,
                            style = MaterialTheme.typography.bodySmall,
                            textAlign = TextAlign.Center,
                            color = color,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                alwaysShowLabel = false
            )
        }
    }
}

@Preview(widthDp = 360)
@Composable
private fun DVXBottomAppBarPreview() = DVXTheme {
    DVXBottomNavigation()
}