package dvx.news.app.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dvx.news.app.R

@Composable
fun CircleMarker(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Row(modifier = modifier) {
        Image(
            painter = painterResource(R.drawable.dot),
            contentDescription = null,
            modifier = Modifier.padding(
                top = 20.dp,
                end = 15.dp
            ),
        )
        content()
    }
}