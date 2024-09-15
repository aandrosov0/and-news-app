package dvx.news.app.components

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dvx.news.app.R
import dvx.news.app.themes.DVXTheme

@Composable
fun EligibilityNotificationBox(
    onAbort: () -> Unit,
    onApprove: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(shape = RoundedCornerShape(8.dp))
            .background(color = MaterialTheme.colorScheme.surfaceContainer)
            .padding(all = 20.dp)
    ) {
        Text(text = stringResource(R.string.eligibility_title))
        Text(
            text = stringResource(R.string.eligibility_text),
            fontSize = 18.sp,
            color = Color.Unspecified.copy(alpha = 0.64f),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(vertical = 10.dp)
        )
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            TextButton(
                onClick = onAbort,
                shape = RoundedCornerShape(8.dp),
            ) {
                Text(
                    text = stringResource(R.string.abort),
                    fontSize = 12.sp
                )
            }
            Spacer(
                modifier = Modifier
                    .padding(start = 24.dp)
            )
            Button(
                onClick = onApprove,
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.settings),
                    fontSize = 12.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
private fun EligibilityNotificationBoxPreview() = DVXTheme {
    EligibilityNotificationBox(
        onAbort = {},
        onApprove = {}
    )
}