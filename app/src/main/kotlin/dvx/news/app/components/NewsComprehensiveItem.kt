package dvx.news.app.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dvx.news.app.R
import dvx.news.app.themes.DVXTheme

@Composable
fun NewsComprehensiveItem(
    time: String,
    type: String,
    imageId: Int,
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
        ComprehensivePostCard(
            time = time,
            type = type,
            imageId = imageId,
            title = title,
            description = description,
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
        imageId = R.drawable.post_img_preview,
        title = "Polizei-Grobeinsatz in Radeberg",
        description = "Polizei schiebt Messer-Angreifer (54) ins",
        onClick = { }
    )
}
