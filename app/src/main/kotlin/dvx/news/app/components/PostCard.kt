package dvx.news.app.components

import dvx.news.app.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import dvx.news.app.themes.DVXTheme

@Composable
fun PostCard(
    title: String,
    description: String,
    imagePainter: Painter,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(268.dp)
            .wrapContentHeight()
    ) {
        Image(
            painter = imagePainter,
            contentDescription = null
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp)
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun PostCardPreview() = DVXTheme {
    PostCard(
        title = "Hamburg weit abgeschlagen",
        description = "Deutsches Burger-Mekka ist...",
        imagePainter = painterResource(R.drawable.img_preview)
    )
}