package dvx.news.app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dvx.news.app.R
import dvx.news.app.themes.DVXTheme
import kotlin.math.roundToInt


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AudioPlayer(
    trackTitle: String,
    trackDuration: Int,
    playbackSpeed: Float,
    isTrackPlaying: Boolean,
    isTrackLoading: Boolean,
    currentTrackPosition: Int,
    onActionButtonClick: () -> Unit,
    onPositionChangeFinished: (Int) -> Unit,
    onPlaybackSpeedButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    var sliderPosition by remember { mutableIntStateOf(0) }
    val sliderInteractionSource = remember { MutableInteractionSource() }
    val isDraggedSlider by sliderInteractionSource.collectIsDraggedAsState()

    val sliderCurrentPosition = if (isDraggedSlider) {
        sliderPosition
    } else {
        currentTrackPosition
    }

    val minutes = (trackDuration - sliderCurrentPosition) / 60000
    val seconds = ((trackDuration - sliderCurrentPosition) % 60000) / 1000

    val currentTrackPositionText = "-$minutes:${if (seconds < 10) "0" else ""}$seconds"
    val actionButtonIcon = if (isTrackPlaying) {
        R.drawable.ic_stop
    } else {
        R.drawable.ic_play
    }

    Surface(
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 8.dp,
        modifier = modifier
            .height(IntrinsicSize.Min)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .fillMaxHeight()
                    .width(90.dp)
            ) {
                if (isTrackLoading) {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier
                            .size(32.dp)
                    )
                } else {
                    Icon(
                        painter = painterResource(actionButtonIcon),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(64.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = ripple(),
                                onClick = onActionButtonClick
                            )
                    )
                }
            }
            Column(
                Modifier
                    .padding(top = 8.dp, end = 8.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = trackTitle,
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.bodySmall
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            text = "${playbackSpeed}x",
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = ripple(color = MaterialTheme.colorScheme.primary),
                                    onClick = onPlaybackSpeedButtonClicked
                                )
                                .padding(horizontal = 8.dp)
                        )
                        Text(
                            text = currentTrackPositionText,
                            fontSize = 16.sp,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
                Slider(
                    value = sliderCurrentPosition.toFloat(),
                    valueRange = 0f..trackDuration.toFloat(),
                    interactionSource = sliderInteractionSource,
                    onValueChange = { sliderPosition = it.roundToInt() },
                    onValueChangeFinished = { onPositionChangeFinished(sliderPosition) },
                    thumb = {
                        Spacer(
                            modifier = Modifier
                                .size(24.dp)
                                .background(
                                    color = MaterialTheme.colorScheme.primary,
                                    shape = RoundedCornerShape(32.dp)
                                )
                        )
                    },
                    track = {
                        Spacer(
                            modifier = Modifier
                                .height(4.dp)
                                .fillMaxWidth()
                                .alpha(0.35f)
                                .background(
                                    color = MaterialTheme.colorScheme.onSurface,
                                    shape = RoundedCornerShape(8.dp)
                                )
                        )
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun AudioPlayerPreview() = DVXTheme {
    AudioPlayer(
        trackTitle = "",
        trackDuration = 60000,
        playbackSpeed = 1f,
        isTrackPlaying = false,
        isTrackLoading = false,
        currentTrackPosition = 0,
        onActionButtonClick = {},
        onPlaybackSpeedButtonClicked = {},
        onPositionChangeFinished = {},
    )
}