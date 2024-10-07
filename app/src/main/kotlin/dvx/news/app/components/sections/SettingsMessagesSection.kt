package dvx.news.app.components.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import dvx.news.app.components.EligibilityNotificationBox
import dvx.news.app.themes.DVXTheme

@Composable
fun SettingsMessagesSection(modifier: Modifier = Modifier) {
    var showed by remember { mutableStateOf(true) }
    if (showed) {
        Dialog(
            onDismissRequest = { showed = false },
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            )
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(color = Color.Gray.copy(alpha = 0.4f))
                    .fillMaxSize()
            ) {
                EligibilityNotificationBox(
                    onApprove = { showed = false },
                    onAbort = { showed = false },
                    modifier = Modifier
                        .padding(16.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun SettingsMessagesSectionPrev() = DVXTheme {
    SettingsMessagesSection()
}