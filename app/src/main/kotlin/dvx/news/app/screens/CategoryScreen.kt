package dvx.news.app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dvx.news.app.R
import dvx.news.app.components.VerticalPostCard
import dvx.news.app.states.Destination
import dvx.news.app.themes.DVXTheme

@Composable
fun CategoryScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Image(
            painter = painterResource(R.drawable.img_small_preview),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(color = MaterialTheme.colorScheme.primary),
                    onClick = { navController.navigate(Destination.Article) }
                )
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
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(color = MaterialTheme.colorScheme.primary),
                    onClick = { navController.navigate(Destination.Article) }
                )
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
                            modifier = Modifier.weight(1f),
                            onClick = { navController.navigate(Destination.Article) }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun CategoryScreenPreview() = DVXTheme {
    CategoryScreen(navController = rememberNavController())
}