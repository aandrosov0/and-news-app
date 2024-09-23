package dvx.news.app.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dvx.news.app.R
import dvx.news.app.themes.DVXTheme

@Composable
fun PostCardWithHeader(
    time: String,
    type: String,
    imageId: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(456.dp)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(size = 8.dp)
            )
    ) {
        ComprehensivePostCardHeader(
            time = time,
            type = type,
        )
        Image(
            painter = painterResource(imageId),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .clip(
                    shape = RoundedCornerShape(
                        bottomStart = 8.dp,
                        bottomEnd = 8.dp
                    )
                )
        )
    }
}

@Preview
@Composable
private fun PostCardWithHeaderPreview() = DVXTheme {
    PostCardWithHeader(
        time = "12:38 Uhr",
        type = "Regional",
        imageId = R.drawable.post_img_preview,
    )
}