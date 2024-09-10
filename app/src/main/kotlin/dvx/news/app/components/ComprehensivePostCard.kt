package dvx.news.app.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dvx.news.app.R
import dvx.news.app.themes.DVXTheme

@Composable
fun ComprehensivePostCardHeader(
    time: String,
    type: String,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(
                    topStart = 8.dp,
                    topEnd = 8.dp
                )
            )
            .size(
                width = 456.dp,
                height = 56.dp
            )
            .padding(
                vertical = 8.dp,
                horizontal = 10.dp
            )
    ) {
        Text(
            text = time,
            style = MaterialTheme.typography.labelMedium,
            color = Color(0xFF484C56),
            modifier = Modifier
                .alpha(0.64f)
        )
        Text(
            text = type,
            style = MaterialTheme.typography.labelMedium,
            color = Color(0xFF484C56),
            modifier = Modifier
                .alpha(0.64f)
        )
    }
}

@Composable
fun ComprehensivePostCardFooter(
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(
                    bottomStart = 8.dp,
                    bottomEnd = 8.dp
                )
            )
            .width(456.dp)
            .padding(
                top = 9.dp,
                start = 8.dp,
                end = 8.dp,
                bottom = 10.5.dp
            )
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = description,
            fontSize = 18.5.sp,
            lineHeight = 20.sp,
            color = Color.Black,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
fun ComprehensivePostCard(
    time: String,
    type: String,
    imageId: Int,
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(456.dp)
            .shadow(elevation = 8.dp)
    ) {
        ComprehensivePostCardHeader(
            time = time,
            type = type,
        )
        Image(
            painter = painterResource(imageId),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
        )
        ComprehensivePostCardFooter(
            title = title,
            description = description
        )
    }
}

@Preview
@Composable
private fun ComprehensivePostCardHeaderPreview() = DVXTheme {
    ComprehensivePostCardHeader(
        time = "12:38 Uhr",
        type = "Regional",
    )
}

@Preview
@Composable
private fun ComprehensivePostCardFooterPreview() = DVXTheme {
    ComprehensivePostCardFooter(
        title = "Polizei-Grobeinsatz in Radeberg",
        description = "Polizei schiebt Messer-Angreifer (54) ins"
    )
}

@Preview
@Composable
private fun ComprehensivePostCardPreview() = DVXTheme {
    ComprehensivePostCard(
        time = "12:38 Uhr",
        type = "Regional",
        imageId = R.drawable.post_img_preview,
        title = "Polizei-Grobeinsatz in Radeberg",
        description = "Polizei schiebt Messer-Angreifer (54) ins"
    )
}