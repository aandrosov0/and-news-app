package dvx.news.app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dvx.news.app.R
import dvx.news.app.components.PostCard
import dvx.news.app.themes.DVXTheme

@Composable
fun CategoryScreen(modifier: Modifier = Modifier) = Column(
    modifier = modifier
        .verticalScroll(rememberScrollState())
        .background(color = Color.White)
) {
    Image(
        painter = painterResource(R.drawable.img_small_preview),
        contentDescription = null,
        modifier = Modifier.fillMaxWidth()
    )
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 20.dp)
    ) {
        Text(
            text = "FOOD",
            fontSize = 34.sp,
            fontWeight = FontWeight.ExtraBold,
        )
        Icon(
            imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
            contentDescription = null,
            modifier = Modifier
                .padding(start = 13.dp)
        )
    }
    Image(
        painter = painterResource(R.drawable.img_large_preview),
        contentDescription = null,
        modifier = Modifier.fillMaxWidth()
    )
    Column(
        verticalArrangement = Arrangement.spacedBy(11.dp),
        modifier = Modifier.padding(
            start = 10.dp, end = 10.dp,
            top = 10.dp, bottom = 103.dp
        )
    ) {
        repeat(2) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                repeat(2) {
                    PostCard(
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