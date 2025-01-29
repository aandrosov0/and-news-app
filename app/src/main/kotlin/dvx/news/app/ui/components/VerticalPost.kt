package dvx.news.app.ui.components

import dvx.news.app.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import dvx.news.app.ui.themes.DVXTheme

@Composable
fun VerticalPost(
    image: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    title: String? = null,
    description: String? = null
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .width(268.dp)
            .width(intrinsicSize = IntrinsicSize.Min)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(
                    bounded = true,
                    color = MaterialTheme.colorScheme.primary
                ),
                onClick = onClick
            )
    ) {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
        )

        if (title != null) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(top = 6.dp)
                    .fillMaxWidth()
            )
        }

        if (description != null) {
            Text(
                text = description,
                fontSize = 20.sp,
                style = MaterialTheme.typography.bodyLarge,
                lineHeight = 20.sp,
                letterSpacing = (-0.3).sp,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
private fun VerticalPostCardPreview() = DVXTheme {
    VerticalPost(
        title = "Hamburg weit abgeschlagen",
        description = "Deutsches Burger-Mekka ist...",
        image = painterResource(R.drawable.img_preview),
        onClick = {}
    )
}