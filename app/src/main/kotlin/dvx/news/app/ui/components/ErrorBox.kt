package dvx.news.app.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dvx.news.app.themes.DVXTheme
import dvx.news.app.R

@Composable
fun ErrorBox(
    icon: Painter,
    error: String,
    action: String,
    onActionClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.height(64.dp))
        Text(
            text = error,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            lineHeight = 32.sp,
            style = MaterialTheme.typography.headlineMedium,
        )
        TextButton(onClick = onActionClick) {
            Text(
                text = action,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelLarge,
            )
        }
    }
}

@Preview
@Composable
private fun ErrorBoxPreview() {
    DVXTheme {
        ErrorBox(
            icon = painterResource(R.drawable.ic_signal_disconnected),
            error = "Something went wrong\nTry to refresh...",
            action = "refresh",
            onActionClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}