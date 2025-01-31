package dvx.news.app.ui.states

import androidx.compose.runtime.Composable
import dvx.news.data.models.Category
import dvx.news.app.R
import kotlinx.serialization.Serializable

@Serializable
data class CategoryUiState(
    val id: Long = 0,
    val name: String = "",
)

@Serializable
val CategoryUiState.iconId: Int
    @Composable get() = when (id) {
        55L -> R.drawable.ic_car
        52L -> R.drawable.ic_soccer
        18L -> R.drawable.ic_lifestyle
        30L -> R.drawable.ic_news
        196L -> R.drawable.ic_games
        41L -> R.drawable.ic_sport
        33L -> R.drawable.ic_unterhaltung
        106L -> R.drawable.ic_erotic
        31L -> R.drawable.ic_politic
        8L -> R.drawable.ic_advisor
        104L -> R.drawable.ic_health
        105L -> R.drawable.ic_deal
        else -> R.drawable.img_preview
    }

fun Category.toUiState() = CategoryUiState(
    id = id,
    name = name
)