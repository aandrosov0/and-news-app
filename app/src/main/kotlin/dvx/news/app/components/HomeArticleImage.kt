package dvx.news.app.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage
import dvx.news.app.R

@Composable
fun HomeArticleImage(
    imageUrl: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        modifier = modifier
            .fillMaxSize()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(color = MaterialTheme.colorScheme.primary),
                onClick = onClick
            ),
        placeholder = painterResource(R.drawable.img_large_preview),
        error = painterResource(R.drawable.img_large_preview),
        contentScale = ContentScale.FillWidth,
    )
}