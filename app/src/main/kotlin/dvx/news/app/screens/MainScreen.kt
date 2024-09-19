package dvx.news.app.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dvx.news.app.states.Destination
import dvx.news.app.states.Destination.*
import dvx.news.app.themes.DVXTheme

@Composable
fun MainScreen(
    onDestinationChange: (Destination) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        Button(onClick = { onDestinationChange(Home) }) {
            Text(text = "HOME")
        }
        Button(onClick = { onDestinationChange(News) }) {
            Text(text = "NEWS")
        }
        Button(onClick = { onDestinationChange(Category) }) {
            Text(text = "CATEGORY")
        }
        Button(onClick = { onDestinationChange(Article) }) {
            Text(text = "ARTICLE")
        }
        Button(onClick = { onDestinationChange(Overview) }) {
            Text(text = "OVERVIEW")
        }
        Button(onClick = { onDestinationChange(Settings) }) {
            Text(text = "SETTINGS")
        }
        Button(onClick = { onDestinationChange(Includes) }) {
            Text(text = "INCLUDES")
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() = DVXTheme {
    MainScreen(onDestinationChange = {})
}