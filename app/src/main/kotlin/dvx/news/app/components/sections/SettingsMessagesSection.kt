package dvx.news.app.components.sections

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dvx.news.app.R
import dvx.news.app.components.NotificationAlertDialog
import dvx.news.app.themes.DVXTheme

@Composable
fun SettingsMessagesSection() {
    Column(modifier = Modifier.fillMaxSize()) {
        var isOpenedAlertDialog by remember { mutableStateOf(true) }
        when {
            isOpenedAlertDialog -> NotificationAlertDialog(
                title = stringResource(R.string.eligibility_title),
                text = stringResource(R.string.eligibility_text),
                onConfirm = { isOpenedAlertDialog = false },
                onDismiss = { isOpenedAlertDialog = false },
            )
        }
    }
}

@Preview
@Composable
private fun SettingsMessagesSectionPrev() = DVXTheme {
    SettingsMessagesSection()
}