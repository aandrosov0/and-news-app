package dvx.news.app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dvx.news.app.R
import dvx.news.app.components.VerticalPostCard
import dvx.news.app.themes.DVXTheme

@Composable
fun CategoryScreen(modifier: Modifier = Modifier) = Column(
    modifier = modifier
        .verticalScroll(rememberScrollState())
) {
    Image(
        painter = painterResource(R.drawable.img_small_preview),
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .fillMaxWidth()
    )
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(
                horizontal = 10.dp,
                vertical = 20.dp
            )
    ) {
        Text(
            text = stringResource(R.string.food).uppercase(),
            fontSize = 34.sp,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Icon(
            imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.64f),
            modifier = Modifier
                .padding(start = 13.dp)
                .size(28.dp)
        )
    }
    Image(
        painter = painterResource(R.drawable.img_large_preview),
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .fillMaxWidth()
    )
    Column(
        verticalArrangement = Arrangement.spacedBy(11.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(
                start = 10.dp, end = 10.dp,
                top = 10.dp, bottom = 103.dp
            ).fillMaxWidth()
    ) {
        repeat(2) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.width(intrinsicSize = IntrinsicSize.Min)
            ) {
                repeat(2) {
                    VerticalPostCard(
                        title = "Hamburg weit abgeschlagen",
                        description = "Deutsches Burger-Mekka ist...",
                        imagePainter = painterResource(R.drawable.img_preview),
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CategoryScreenPreview() = DVXTheme {
    CategoryScreen()
}