package dvx.news.app.components

import androidx.compose.runtime.Composable
import dvx.news.app.states.Destination

@Composable
fun AppBottomBar(
    destination: Destination,
    onDestinationChange: (Destination) -> Unit
) {
    when (destination) {
        Destination.Main -> {}
        else -> DVXBottomNavigation(
            current = destination,
            onDestinationSelect = { onDestinationChange(it) }
        )
    }
}