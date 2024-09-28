package dvx.news.app.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.Composable
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
fun PlayerViewHeader(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Text(
            text = "Artikel anhören",
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
            text = "-04:23",
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .padding(start = 26.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerView(modifier: Modifier = Modifier) {
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
        Image(
            painter = painterResource(R.drawable.ic_play),
            contentDescription = null,
        )
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(all = 9.dp)
                .fillMaxHeight()
        ) {
            PlayerViewHeader()
            Slider(
                value = 0f,
                onValueChange = {},
                thumb = {
                    Icon(
                        painter = painterResource(R.drawable.dot),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(size = 16.dp)
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
                modifier = Modifier
                    .height(intrinsicSize = IntrinsicSize.Min)
            )
        }
    }
}

@Preview
@Composable
private fun PlayerViewHeaderPreview() = DVXTheme {
    PlayerViewHeader()
}

@Preview
@Composable
private fun PlayerViewPreview() = DVXTheme {
    PlayerView()
}