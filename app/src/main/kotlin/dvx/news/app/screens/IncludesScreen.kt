package dvx.news.app.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dvx.news.app.R
import dvx.news.app.components.HorizontalPostCard
import dvx.news.app.components.PlayerView
import dvx.news.app.components.VerticalPostCard
import dvx.news.app.states.Audio
import dvx.news.app.states.Destination
import dvx.news.app.themes.DVXTheme
import dvx.news.app.viewModels.AudioViewModel

@Composable
fun IncludesScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    audioViewModel: AudioViewModel = AudioViewModel()
) {
    val audioState by audioViewModel.state.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(
                start = 10.dp,
                end = 10.dp,
                bottom = 10.dp
            )
            .verticalScroll(rememberScrollState()),
    ) {
        PlayerView(
            title = "Bethoven",
            position = audioViewModel.positionInMillis,
            duration = audioViewModel.durationInMillis,
            playing = audioState == Audio.PLAYING,
            onPlay = {
                audioViewModel.start("https://upload.wikimedia.org/wikipedia/commons/e/eb/Beethoven_Moonlight_1st_movement.ogg")
            },
            modifier = Modifier
                .padding(top = 30.dp)
                .height(80.dp)
        )
        HorizontalDivider(
            modifier = Modifier
                .padding(top = 30.dp)
        )
        HorizontalPostCard(
            title = "Sei sind die Beeeesten’’",
            description = "Uefa verandert beruhmte Champions-League-Hymne!",
            imagePainter = painterResource(R.drawable.img_preview),
            onClick = { navController.navigate(Destination.Article) },
            modifier = Modifier
                .padding(vertical = 10.dp)
        )
        HorizontalDivider(
            modifier = Modifier
                .padding(bottom = 36.dp)
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(space = 10.dp),
            modifier = Modifier
                .width(intrinsicSize = IntrinsicSize.Min)
        ) {
            repeat(2) {
                VerticalPostCard(
                    title = "Hamburg weit abgeschlagen",
                    description = "Deutsches Burger-Mekka ist...",
                    imagePainter = painterResource(R.drawable.img_preview),
                    onClick = { navController.navigate(Destination.Article) },
                    modifier = Modifier
                        .weight(1f),
                )
            }
        }
        VerticalPostCard(
            title = "Schutz vor tollen Sachen",
            description = "Ruckgang von HPV-Impfung bei Kindern",
            imagePainter = painterResource(R.drawable.img_small_preview),
            onClick = { navController.navigate(Destination.Article) },
            modifier = Modifier
                .padding(top = 22.dp)
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun IncludesScreenPreview() = DVXTheme {
    IncludesScreen(
        navController = rememberNavController()
    )
}