package dvx.news.app.states

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dvx.news.app.R
import kotlinx.serialization.Serializable

@Serializable
sealed class Destination {
    @Serializable
    data class Article(val id: Long) : Destination()
    @Serializable
    data object Home : Destination()
    @Serializable
    data object Overview : Destination()
    @Serializable
    data class Category(val id: Long, val name: String) : Destination()
    @Serializable
    data object Settings : Destination()
    @Serializable
    data object Menu : Destination()
    @Serializable
    data class News(val initialTab: NewsTab) : Destination()
    @Serializable
    data object Includes : Destination()
}

val Destination.localizedName
    @Composable
    get() = when (this) {
        Destination.Home -> stringResource(R.string.home)
        is Destination.Menu -> stringResource(R.string.menu)
        is Destination.Article -> stringResource(R.string.article)
        is Destination.Category -> name
        is Destination.News -> stringResource(R.string.news)
        Destination.Overview -> stringResource(R.string.overview)
        Destination.Settings -> stringResource(R.string.settings)
        Destination.Includes -> stringResource(R.string.includes)
    }

val Destination.localizedIcon
    @Composable
    get() = when (this) {
        Destination.Home -> painterResource(R.drawable.ic_startseite)
        is Destination.Menu -> painterResource(R.drawable.ic_mehr)
        is Destination.Category -> painterResource(CategoryUiState(id).iconId)
        else -> rememberVectorPainter(
            ImageVector.Builder(
                defaultWidth = 20.dp,
                defaultHeight = 20.dp,
                viewportWidth = 20f,
                viewportHeight = 20f,
            ).build()
        )
    }