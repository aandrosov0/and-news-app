package dvx.news.app.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dvx.news.app.ui.themes.DVXTheme
import dvx.news.app.R

@Composable
fun NotificationAlertDialog(
    title: String,
    text: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val buttonTextStyle = MaterialTheme.typography.bodySmall.copy(
        fontWeight = FontWeight.ExtraBold
    )
    val defaultNodeShape = RoundedCornerShape(8.dp)
    AlertDialog(
        title = {
            Text(
                text = title,
                fontSize = 18.sp,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        text = {
            Text(
                text = text,
                fontSize = 14.5.sp,
                modifier = Modifier
                    .alpha(.64f)
            )
        },
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = onConfirm,
                shape = defaultNodeShape
            ) {
                Text(
                    text = stringResource(R.string.settings).uppercase(),
                    color = Color.White,
                    style = buttonTextStyle
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                shape = defaultNodeShape
            ) {
                Text(
                    text = stringResource(R.string.abort).uppercase(),
                    style = buttonTextStyle,
                    modifier = Modifier
                        .alpha(.4f)
                )
            }
        },
        shape = defaultNodeShape,
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = modifier
    )
}

@Preview
@Composable
private fun EligibilityNotificationBoxPreview() = DVXTheme {
    NotificationAlertDialog(
        title = stringResource(R.string.eligibility_title),
        text = stringResource(R.string.eligibility_text),
        onConfirm = {},
        onDismiss = {}
    )
}