package dvx.news.app.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dvx.news.app.R
import dvx.news.app.themes.DVXTheme

@Composable
fun AudioPlayerHeader(
    title: String,
    position: Int,
    modifier: Modifier = Modifier
) {
    val minutes = position / 60000
    val seconds = (position % 60000) / 1000

    Row(modifier = modifier) {
        Text(
            text = title,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Spacer(
            modifier = Modifier
                .weight(1f)
        )
        Text(
            text = "1x",
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Text(
            text = "$minutes:${if (seconds < 10) "0" else ""}$seconds",
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .padding(start = 26.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AudioPlayerSlider(
    duration: Int,
    position: Int,
    onHover: () -> Unit,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    if (isHovered) {
        onHover()
    }

    Slider(
        valueRange = 0f..duration.toFloat(),
        value = position.toFloat(),
        interactionSource = interactionSource,
        onValueChange = { onValueChange(it.toInt()) },
        thumb = {
            Icon(
                painter = painterResource(R.drawable.dot),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(size = 16.dp)
                    .hoverable(interactionSource = interactionSource)
            )
        },
        track = {
            Icon(
                painter = painterResource(R.drawable.bar),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .fillMaxWidth()
            )
        },
        modifier = modifier
            .height(intrinsicSize = IntrinsicSize.Min)
    )
}

@Composable
fun AudioPlayer(
    title: String,
    position: Int,
    duration: Int,
    playing: Boolean,
    onHover: () -> Unit,
    onPlay: () -> Unit,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(size = 8.dp)
            )
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(size = 8.dp)
            )
            .height(intrinsicSize = IntrinsicSize.Min)
            .widthIn(max = 450.dp)
    ) {
        val icon = if (!playing) R.drawable.ic_play else R.drawable.ic_stop
        Image(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(),
                    onClick = onPlay
                )
        )
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(all = 9.dp)
                .fillMaxHeight()
        ) {
            AudioPlayerHeader(
                title = title,
                position = position
            )
            AudioPlayerSlider(
                duration = duration,
                position = position,
                onHover = onHover,
                onValueChange = onValueChange,
            )
        }
    }
}

@Preview
@Composable
private fun AudioPlayerHeaderPreview() = DVXTheme {
    AudioPlayerHeader(
        title = "",
        position = 0
    )
}

@Preview
@Composable
private fun AudioPlayerPreview() = DVXTheme {
    AudioPlayer(
        title = "Bethoven",
        duration = 0,
        position = 0,
        playing = true,
        onPlay = {},
        onValueChange = {},
        onHover = {},
    )
}