package dvx.news.app.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dvx.news.app.R
import dvx.news.app.themes.DVXTheme

@Composable
fun NewsTabContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Alle Ressorts in der Übersicht",
            color = Color(0xFF18191C).copy(alpha = 0.64f),
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 12.dp,
                    bottom = 20.dp
                )
        )
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .height(IntrinsicSize.Min)
        ) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.line),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxHeight()
                    .offset(
                        x = 6.dp,
                        y = 20.dp
                    )
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .padding(bottom = 40.dp)
            ) {
                CircleMarker {
                    ComprehensivePostCard(
                        time = "12:38 Uhr",
                        type = "Regional",
                        imageId = R.drawable.post_img_preview,
                        title = "Polizei-Grobeinsatz in Radeberg",
                        description = "Polizei schiebt Messer-Angreifer (54) ins",
                    )
                }
                CircleMarker {
                    ComprehensivePostCard(
                        time = "12:34 Uhr",
                        type = "Leben & Wissen",
                        imageId = R.drawable.post_img_preview,
                        title = "Schutz vor tollen Sachen",
                        description = "Ruckgang von HPV-Impfung bei Kindern",
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun NewsTabContentPreview() = DVXTheme {
    NewsTabContent()
}