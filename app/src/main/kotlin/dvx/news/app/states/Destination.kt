package dvx.news.app.states

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dvx.news.app.R
import kotlinx.serialization.Serializable

sealed class Destination {
    @Serializable
    data object Article : Destination()
    @Serializable
    data object Category : Destination()
    @Serializable
    data object Entertainment : Destination()
    @Serializable
    data object Home : Destination()
    @Serializable
    data object Overview : Destination()
    @Serializable
    data object Sport : Destination()
    @Serializable
    data object Settings : Destination()
    @Serializable
    data object Lifestyle : Destination()
    @Serializable
    data object Menu : Destination()
    @Serializable
    data object News : Destination()
    @Serializable
    data object Includes : Destination()
}

val Destination.localizedName
    @Composable
    get() = when (this) {
        Destination.Entertainment -> stringResource(R.string.entertainment)
        Destination.Home -> stringResource(R.string.home)
        Destination.Lifestyle -> stringResource(R.string.lifestyle)
        Destination.Menu -> stringResource(R.string.menu)
        Destination.Sport -> stringResource(R.string.sport)
        Destination.Article -> stringResource(R.string.article)
        Destination.Category -> stringResource(R.string.category)
        Destination.News -> stringResource(R.string.news)
        Destination.Overview -> stringResource(R.string.overview)
        Destination.Settings -> stringResource(R.string.settings)
        Destination.Includes -> stringResource(R.string.includes)
    }

val Destination.localizedIcon
    @Composable
    get() = when (this) {
        Destination.Entertainment -> painterResource(R.drawable.ic_unterhaltung)
        Destination.Home -> painterResource(R.drawable.ic_startseite)
        Destination.Lifestyle -> painterResource(R.drawable.ic_lifestyle)
        Destination.Menu -> painterResource(R.drawable.ic_mehr)
        Destination.Sport -> painterResource(R.drawable.ic_sport)
        else -> rememberVectorPainter(
            ImageVector.Builder(
                defaultWidth = 20.dp,
                defaultHeight = 20.dp,
                viewportWidth = 20f,
                viewportHeight = 20f,
            ).build()
        )
    }