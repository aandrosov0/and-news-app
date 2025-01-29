package dvx.news.app.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dvx.news.app.R
import dvx.news.app.ui.themes.DVXTheme

@Composable
fun NewsComprehensiveItem(
    time: String,
    type: String,
    image: Painter,
    title: String,
    description: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(space = 12.dp),
        modifier = modifier.height(intrinsicSize = IntrinsicSize.Min)
    ) {
        VerticalDirectionLine(
            modifier = Modifier
                .offset(y = 23.dp)
                .fillMaxHeight()
        )
        PostCard(
            time = time,
            type = type,
            image = image,
            subheadline = title,
            headline = description,
            onClick = onClick,
            modifier = Modifier
                .padding(bottom = 10.dp)
        )
    }
}

@Preview
@Composable
private fun NewsComprehensiveItemPreview() = DVXTheme {
    NewsComprehensiveItem(
        time = "12:38 Uhr",
        type = "Regional",
        image = painterResource(R.drawable.post_img_preview),
        title = "Polizei-Grobeinsatz in Radeberg",
        description = "Polizei schiebt Messer-Angreifer (54) ins",
        onClick = { }
    )
}
