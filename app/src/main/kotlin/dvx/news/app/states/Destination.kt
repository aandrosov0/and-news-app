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
sealed class Destination

@Serializable
data class ArticleScreen(val id: Long) : Destination()

@Serializable
data object HomeScreen : Destination()

@Serializable
data object OverviewScreen : Destination()

@Serializable
data class CategoryScreen(val id: Long, val name: String) : Destination()

@Serializable
data object SettingsScreen : Destination()

@Serializable
data object MenuScreen : Destination()

@Serializable
data class NewsScreen(val initialTab: NewsTab) : Destination()

@Serializable
data object IncludesScreen : Destination()

val Destination.localizedName
    @Composable
    get() = when (this) {
        HomeScreen -> stringResource(R.string.home)
        is MenuScreen -> stringResource(R.string.menu)
        is ArticleScreen -> stringResource(R.string.article)
        is CategoryScreen -> name
        is NewsScreen -> stringResource(R.string.news)
        OverviewScreen -> stringResource(R.string.overview)
        SettingsScreen -> stringResource(R.string.settings)
        IncludesScreen -> stringResource(R.string.includes)
    }

val Destination.localizedIcon
    @Composable
    get() = when (this) {
        HomeScreen -> painterResource(R.drawable.ic_startseite)
        is MenuScreen -> painterResource(R.drawable.ic_mehr)
        is CategoryScreen -> painterResource(CategoryUiState(id).iconId)
        else -> rememberVectorPainter(
            ImageVector.Builder(
                defaultWidth = 20.dp,
                defaultHeight = 20.dp,
                viewportWidth = 20f,
                viewportHeight = 20f,
            ).build()
        )
    }