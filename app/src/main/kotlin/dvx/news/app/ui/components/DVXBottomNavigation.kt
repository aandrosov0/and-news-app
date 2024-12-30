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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dvx.news.app.states.Destination
import dvx.news.app.states.localizedIcon
import dvx.news.app.states.localizedName
import dvx.news.app.themes.DVXTheme
import dvx.news.app.R

data class DVXNavigationItemColors(
    val selected: Color,
    val unselected: Color
)

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DVXBottomNavigation(
    destination: Destination,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val destinations = listOf(
        Destination.Home,
        Destination.Sport,
        Destination.Lifestyle,
        Destination.Entertainment,
        Destination.Menu
    )

    var current by remember { mutableStateOf(destinations.first()) }
    if (destination in destinations) {
        current = destination
    }

    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        destinations.forEach { destination ->
            DVXNavigationItem(
                selected = current == destination,
                icon = destination.localizedIcon,
                onClick = { navController.navigate(destination) },
                modifier = Modifier.weight(1f),
                label = destination.localizedName
            )
        }
    }
}

@Preview
@Composable
private fun DVXBottomAppBarPreview() = DVXTheme {
    DVXBottomNavigation(
        destination = Destination.Home,
        navController = rememberNavController()
    )
}