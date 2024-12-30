package dvx.news.app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dvx.news.app.R
import dvx.news.app.themes.DVXTheme

@Composable
private fun Header(
    time: String,
    type: String,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surface,
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
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .alpha(0.64f)
        )
        Text(
            text = type,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .alpha(0.64f)
        )
    }
}

@Composable
private fun Footer(
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surface,
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
            .clip(
                shape = RoundedCornerShape(
                    bottomStart = 8.dp,
                    bottomEnd = 8.dp
                )
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
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
fun PostCard(
    time: String,
    type: String,
    image: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    title: String? = null,
    description: String? = null
) {
    Column(
        modifier = modifier
            .width(456.dp)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(size = 8.dp)
            ).clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(
                    color = MaterialTheme.colorScheme.primary
                ),
                onClick = onClick
            )
    ) {
        Header(
            time = time,
            type = type,
        )
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
        )

        if (title != null || description != null) {
            Footer(
                title = title ?: "",
                description = description ?: ""
            )
        }
    }
}

@Preview
@Composable
private fun HeaderPreview() = DVXTheme {
    Header(
        time = "12:38 Uhr",
        type = "Regional",
    )
}

@Preview
@Composable
private fun FooterPreview() = DVXTheme {
    Footer(
        title = "Polizei-Grobeinsatz in Radeberg",
        description = "Polizei schiebt Messer-Angreifer (54) ins"
    )
}

@Preview
@Composable
private fun PostCardPreview() = DVXTheme {
    PostCard(
        time = "12:38 Uhr",
        type = "Regional",
        image = painterResource(R.drawable.post_img_preview),
        title = "Polizei-Grobeinsatz in Radeberg",
        description = "Polizei schiebt Messer-Angreifer (54) ins",
        onClick = {}
    )
}