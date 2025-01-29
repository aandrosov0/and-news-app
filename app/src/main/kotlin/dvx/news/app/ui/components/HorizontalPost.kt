package dvx.news.app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dvx.news.app.R
import dvx.news.app.ui.themes.DVXTheme

@Composable
fun HorizontalPost(
    title: String,
    description: String,
    imagePainter: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(color = MaterialTheme.colorScheme.primary),
                onClick = onClick
            ),
        horizontalArrangement = Arrangement.spacedBy(space = 10.dp),
    ) {
        Image(
            painter = imagePainter,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.size(100.dp)
        )
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
            )
            Text(
                text = description,
                fontSize = 20.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 3,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}

@Preview
@Composable
private fun HorizontalPostPreview() = DVXTheme {
    HorizontalPost(
        title = "Hamburg weit abgeschlagen",
        description = "Deutsches Burger-Mekka ist...",
        imagePainter = painterResource(R.drawable.img_preview),
        onClick = {}
    )
}