package dvx.news.app.states

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object Main : Screen()
    @Serializable
    data object Home : Screen()
    @Serializable
    data object News : Screen()
    @Serializable
    data object Category : Screen()
    @Serializable
    data object Article : Screen()
    @Serializable
    data object Overview : Screen()
    @Serializable
    data object Settings : Screen()
}