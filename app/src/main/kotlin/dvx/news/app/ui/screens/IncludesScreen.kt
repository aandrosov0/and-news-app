package dvx.news.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
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
import dvx.news.app.ui.components.AudioPlayer
import dvx.news.app.ui.components.HorizontalPost
import dvx.news.app.ui.components.VerticalPost
import dvx.news.app.states.Destination
import dvx.news.app.themes.DVXTheme
import dvx.news.app.viewModels.AudioViewModel
import org.koin.androidx.compose.koinViewModel

private const val MUSIC_URL = "https://www.chosic.com/wp-content/uploads/2020/06/Karine_Gilanyan_-_Beethoven_-_Piano_Sonata_nr15_in_D_major_op28_Pastoral_-_IV_Rondo_Allegro_ma_non_troppo(chosic.com).mp3"

@Composable
fun IncludesScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    audioViewModel: AudioViewModel = koinViewModel()
) {
    val track by audioViewModel.uiState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .verticalScroll(rememberScrollState())
            .padding(
                start = 10.dp,
                end = 10.dp
            )
    ) {
        AudioPlayer(
            trackTitle = "Artikel anhören",
            trackDuration = track.duration,
            playbackSpeed = track.playbackSpeed,
            isTrackPlaying = track.isPlaying,
            isTrackLoading = track.isLoading,
            currentTrackPosition = track.currentPosition,
            onActionButtonClick = {
                if (track.isPlaying) {
                    audioViewModel.pause()
                } else {
                    audioViewModel.start(MUSIC_URL)
                }
            },
            onPositionChangeFinished = track.onChangePosition,
            onPlaybackSpeedButtonClicked = track.onPlaybackSpeedChange,
            modifier = Modifier
                .padding(top = 16.dp)
        )
        HorizontalDivider(
            modifier = Modifier
                .padding(top = 30.dp)
        )
        HorizontalPost(
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
                VerticalPost(
                    title = "Hamburg weit abgeschlagen",
                    description = "Deutsches Burger-Mekka ist...",
                    image = painterResource(R.drawable.img_preview),
                    onClick = { navController.navigate(Destination.Article) },
                    modifier = Modifier
                        .weight(1f),
                )
            }
        }
        VerticalPost(
            title = "Schutz vor tollen Sachen",
            description = "Ruckgang von HPV-Impfung bei Kindern",
            image = painterResource(R.drawable.img_small_preview),
            onClick = { navController.navigate(Destination.Article) },
            modifier = Modifier
                .padding(top = 22.dp, bottom = 10.dp)
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