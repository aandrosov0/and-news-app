package dvx.news.app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import dvx.news.app.R
import dvx.news.app.ui.themes.DVXTheme

@Composable
fun Thumbnail(
    image: Painter,
    headline: String,
    subheadline: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Image(
            painter = image,
            contentDescription = headline,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
                .drawWithContent {
                    drawContent()
                    drawRect(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black),
                            startY = size.height / 2
                        )
                    )
                }
        )
        ThumbnailInfo(
            headline = subheadline,
            subheadline = headline,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(8.5.dp)
        )
    }
}

@Composable
private fun ThumbnailInfo(
    headline: String,
    subheadline: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Subheadline(text = subheadline)
        Headline(text = headline)
    }
}

@Composable
private fun Headline(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier
            .background(MaterialTheme.colorScheme.primary)
            .padding(horizontal = 2.5.dp),
        color = MaterialTheme.colorScheme.onPrimary,
        textAlign = TextAlign.Center,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
        style = MaterialTheme.typography.labelSmall
    )
}

@Composable
private fun Subheadline(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier,
        color = MaterialTheme.colorScheme.onPrimary,
        fontSize = 20.sp,
        fontWeight = FontWeight.ExtraBold,
        letterSpacing = (-0.5).sp,
        textAlign = TextAlign.Center,
        lineHeight = 0.96.em,
        style = MaterialTheme.typography.headlineMedium
    )
}


@Preview
@Composable
private fun HeadlinePreview() = DVXTheme {
    Headline(text = "Handball-Star überrascht")
}

@Preview
@Composable
private fun SubheadlinePreview() = DVXTheme {
    Subheadline(text = "Dieses Interview ist jetzt schon legendär")
}

@Preview
@Composable
private fun ThumbnailPreview() = DVXTheme {
    Thumbnail(
        image = painterResource(R.drawable.img_rectangle_preview),
        headline = "Handball-Star überrascht",
        subheadline = "Dieses Interview ist jetzt schon legendär",
    )
}

@Preview
@Composable
private fun ThumbnailInfoPreview() = DVXTheme {
    ThumbnailInfo(
        headline = "Handball-Star überrascht",
        subheadline = "Dieses Interview ist jetzt schon legendär"
    )
}