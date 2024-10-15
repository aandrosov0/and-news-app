package dvx.news.app.components.sections

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NewsItemsSectionTitle(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        fontSize = 16.sp,
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier
            .alpha(.64f)
    )
}

@Composable
fun <T> NewsItemsSection(
    title: String,
    items: List<T>,
    modifier: Modifier = Modifier,
    itemContent: @Composable LazyItemScope.(item: T) -> Unit = {}
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        item {
            NewsItemsSectionTitle(
                text = title,
                modifier = Modifier
                    .padding(vertical = 20.dp)
            )
        }
        items(
            items = items,
            itemContent = itemContent
        )
    }
}
