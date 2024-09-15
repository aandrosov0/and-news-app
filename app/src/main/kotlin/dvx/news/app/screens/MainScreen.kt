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
import dvx.news.app.states.Screen
import dvx.news.app.states.Screen.*
import dvx.news.app.themes.DVXTheme

@Composable
fun MainScreen(
    onChangeScreen: (Screen) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        Button(onClick = { onChangeScreen(Home) }) {
            Text(text = "HOME")
        }
        Button(onClick = { onChangeScreen(News) }) {
            Text(text = "NEWS")
        }
        Button(onClick = { onChangeScreen(Category) }) {
            Text(text = "CATEGORY")
        }
        Button(onClick = { onChangeScreen(Article) }) {
            Text(text = "ARTICLE")
        }
        Button(onClick = { onChangeScreen(Overview) }) {
            Text(text = "OVERVIEW")
        }
        Button(onClick = { onChangeScreen(Settings) }) {
            Text(text = "SETTINGS")
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() = DVXTheme {
    MainScreen(onChangeScreen = {})
}