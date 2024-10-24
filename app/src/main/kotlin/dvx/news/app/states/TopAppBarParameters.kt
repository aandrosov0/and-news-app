package dvx.news.app.states

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import dvx.news.app.R

data class TopAppBarParameters(
    val parent: String,
    val destination: String,
    val isShowingLogo: Boolean,
    val isShowingBack: Boolean,
    val isShowingShare: Boolean,
)

object TopAppBarDefaults {
    val topAppBarParameters
        @Composable
        get() = TopAppBarParameters(
            parent = stringResource(R.string.menu),
            destination = "",
            isShowingBack = true,
            isShowingLogo = false,
            isShowingShare = false,
        )
}
