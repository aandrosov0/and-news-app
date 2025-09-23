package com.dvx.news.ui.states

import androidx.compose.runtime.Composable
import dvx.news.data.models.Category
import com.dvx.news.R
import kotlinx.serialization.Serializable

@Serializable
data class CategoryUiState(
    val id: String = "",
    val name: String = "",
    val iconId: String = "",
)

@Serializable
val CategoryUiState.iconResId: Int
    @Composable get() = when (iconId) {
        "ic_car" -> R.drawable.ic_car
        "ic_soccer" -> R.drawable.ic_soccer
        "ic_lifestyle" -> R.drawable.ic_lifestyle
        "ic_news" -> R.drawable.ic_news
        "ic_games" -> R.drawable.ic_games
        "ic_sport" -> R.drawable.ic_sport
        "ic_entertainment" -> R.drawable.ic_entertainment
        "ic_erotic" -> R.drawable.ic_erotic
        "ic_politic" -> R.drawable.ic_politic
        "ic_advisor" -> R.drawable.ic_advisor
        "ic_health" -> R.drawable.ic_health
        "ic_deal" -> R.drawable.ic_deal
        "ic_world" -> R.drawable.ic_world
        "ic_community" -> R.drawable.ic_community
        "ic_culture" -> R.drawable.ic_culture
        "ic_fire" -> R.drawable.ic_fire
        else -> R.drawable.ic_void
    }

fun Category.asState() = CategoryUiState(
    id = id,
    name = name,
    iconId = iconId
)