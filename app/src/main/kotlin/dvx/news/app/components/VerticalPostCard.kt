package dvx.news.app.components

import dvx.news.app.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import dvx.news.app.themes.DVXTheme

@Composable
fun VerticalPostCard(
    title: String,
    description: String,
    imagePainter: Painter,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .width(268.dp)
            .width(intrinsicSize = IntrinsicSize.Min)
    ) {
        Image(
            painter = imagePainter,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            fontSize = 15.sp,
            modifier = Modifier
                .padding(top = 6.dp)
                .fillMaxWidth()
        )
        Text(
            text = description,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyLarge,
            letterSpacing = (-0.3).sp,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun VerticalPostCardPreview() = DVXTheme {
    VerticalPostCard(
        title = "Hamburg weit abgeschlagen",
        description = "Deutsches Burger-Mekka ist...",
        imagePainter = painterResource(R.drawable.img_preview),
    )
}