package dvx.news.app.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dvx.news.app.ui.themes.DVXTheme
import dvx.news.app.R

@Composable
fun ErrorContainer(
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
        CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurface) {
            Icon(
                painter = icon,
                contentDescription = null,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.height(64.dp))
            Error(text = error)
        }
        ActionButton(
            label = action,
            onClick = onActionClick
        )
    }
}

@Composable
private fun ActionButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(
            text = label,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.labelLarge,
        )
    }
}

@Composable
private fun Error(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier,
        color = LocalContentColor.current,
        textAlign = TextAlign.Center,
        lineHeight = 32.sp,
        style = MaterialTheme.typography.headlineMedium,
    )
}

@Preview
@Composable
private fun ErrorContainerPreview() {
    DVXTheme {
        ErrorContainer(
            icon = painterResource(R.drawable.ic_wifi_off),
            error = "Something went wrong\nTry to refresh...",
            action = "refresh",
            onActionClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview
@Composable
private fun ErrorPreview() = DVXTheme {
    Error(text = "Error")
}