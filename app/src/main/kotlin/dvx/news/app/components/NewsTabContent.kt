package dvx.news.app.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
            .height(intrinsicSize = IntrinsicSize.Min)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = stringResource(R.string.news_tab_title),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.64f),
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
        )
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp)
        ) {
            Column {
                Row(horizontalArrangement = Arrangement.spacedBy(space = 12.dp)) {
                    VerticalDirectionLine(
                        modifier = Modifier
                            .requiredHeight(height = 340.dp)
                            .offset(y = 23.dp)
                    )
                    ComprehensivePostCard(
                        time = "12:38 Uhr",
                        type = "Regional",
                        imageId = R.drawable.post_img_preview,
                        title = "Polizei-Grobeinsatz in Radeberg",
                        description = "Polizei schiebt Messer-Angreifer (54) ins",
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(space = 12.dp)) {
                    VerticalDirectionLine(
                        modifier = Modifier
                            .requiredHeight(height = 340.dp)
                            .offset(y = 23.dp)
                    )
                    ComprehensivePostCard(
                        time = "12:38 Uhr",
                        type = "Regional",
                        imageId = R.drawable.post_img_preview,
                        title = "Polizei-Grobeinsatz in Radeberg",
                        description = "Polizei schiebt Messer-Angreifer (54) ins",
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