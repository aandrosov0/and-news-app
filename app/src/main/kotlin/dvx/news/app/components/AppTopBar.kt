package dvx.news.app.components

import androidx.compose.runtime.Composable
import dvx.news.app.states.Destination
import dvx.news.app.states.localizedName

@Composable
fun AppTopBar(
    destination: Destination,
    onNavigateUp: () -> Unit,
) {
    val title = when (destination) {
        Destination.Article, Destination.News -> ""
        else -> destination.localizedName
    }

    val destinationText = when (destination) {
        Destination.Article -> "Zurück"
        else -> Destination.Menu.localizedName
    }

    when (destination) {
        Destination.Main -> {}
        Destination.Home -> DVXTopLogoAppBar()
        else -> DVXTopAppBar(
            title = title,
            destination = destinationText,
            onNavigateUp = onNavigateUp,
            activeExport = destination == Destination.Article
        )
    }
}