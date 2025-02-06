package dvx.news.app.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import dvx.news.app.R
import dvx.news.app.ui.screens.navigation.Category
import dvx.news.app.ui.themes.DVXTheme

data class TopLevelRoute(val name: String, val icon: Painter, val route: Any)

data class DVXNavigationItemColors(
    val selected: Color,
    val unselected: Color
)

@Composable
fun DVXBottomNavigation(
    currentDestination: NavDestination?,
    onDestinationChange: (Any) -> Unit,
    destinations: List<TopLevelRoute>,
    modifier: Modifier = Modifier,
) {
    var categoryId by remember { mutableLongStateOf(0) }
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    ) {
        destinations.forEach { topLevelRoute ->
            var selected = currentDestination?.hasRoute(topLevelRoute.route::class) == true
            if (topLevelRoute.route is Category && selected) {
                selected = categoryId == topLevelRoute.route.id
            }

            DVXNavigationItem(
                selected = selected,
                icon = topLevelRoute.icon,
                onClick = {
                    onDestinationChange(topLevelRoute.route)
                    categoryId = if (topLevelRoute.route is Category) {
                        topLevelRoute.route.id
                    } else {
                        0
                    }
                },
                modifier = Modifier.weight(1f),
                label = topLevelRoute.name
            )
        }
    }
}

@Composable
fun DVXNavigationItem(
    selected: Boolean,
    icon: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    iconColors: DVXNavigationItemColors = DVXNavigationItemColors(
        selected = MaterialTheme.colorScheme.primary,
        unselected = MaterialTheme.colorScheme.onSurface.copy(.5f)
    ),
    labelColors: DVXNavigationItemColors = DVXNavigationItemColors(
        selected = MaterialTheme.colorScheme.onSurface,
        unselected = MaterialTheme.colorScheme.onSurface.copy(.5f)
    )
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .selectable(
                selected = true,
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(false),
                onClick = onClick
            )
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = animateColorAsState(
                targetValue = if (selected) iconColors.selected else iconColors.unselected,
                animationSpec = spring(stiffness = Spring.StiffnessVeryLow),
                label = "Icon Color"
            ).value,
            modifier = Modifier
                .size(32.dp)
                .padding(bottom = 8.dp)
        )
        if (label != null) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                color = animateColorAsState(
                    targetValue = if (selected) labelColors.selected else labelColors.unselected,
                    animationSpec = spring(stiffness = Spring.StiffnessVeryLow),
                    label = "Icon Color"
                ).value
            )
        }
    }
}

@Preview
@Composable
private fun DVXNavigationItemPreview() {
    DVXTheme {
        DVXNavigationItem(
            icon = painterResource(R.drawable.ic_sport),
            selected = true,
            onClick = {},
            label = "Sport",
        )
    }
}

@Preview
@Composable
private fun DVXBottomAppBarPreview() = DVXTheme {
    DVXBottomNavigation(
        currentDestination = NavDestination(""),
        destinations = listOf(
            TopLevelRoute(name = "Home", icon = painterResource(R.drawable.ic_startseite), route = Unit),
            TopLevelRoute(name = "Home", icon = painterResource(R.drawable.ic_mehr), route = Unit),
        ),
        onDestinationChange = {},
    )
}