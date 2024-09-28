package dvx.news.app.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxHeight()
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
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .height(intrinsicSize = IntrinsicSize.Min)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(space = 12.dp),
                modifier = Modifier.height(intrinsicSize = IntrinsicSize.Min)
            ) {
                VerticalDirectionLine(
                    modifier = Modifier
                        .offset(y = 23.dp)
                        .fillMaxHeight()
                )
                ComprehensivePostCard(
                    time = "12:38 Uhr",
                    type = "Regional",
                    imageId = R.drawable.post_img_preview,
                    title = "Polizei-Grobeinsatz in Radeberg",
                    description = "Polizei schiebt Messer-Angreifer (54) ins",
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(space = 12.dp),
                modifier = Modifier.fillMaxHeight()
            ) {
                VerticalDirectionLine(
                    modifier = Modifier
                        .offset(y = 23.dp)
                        .fillMaxHeight()
                )
                ComprehensivePostCard(
                    time = "12:38 Uhr",
                    type = "Regional",
                    imageId = R.drawable.post_img_preview,
                    title = "Polizei-Grobeinsatz in Radeberg",
                    description = "Polizei schiebt Messer-Angreifer (54) ins",
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun NewsTabContentPreview() = DVXTheme {
    NewsTabContent()
}