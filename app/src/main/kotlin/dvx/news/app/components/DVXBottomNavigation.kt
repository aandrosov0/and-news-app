package dvx.news.app.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dvx.news.app.states.Destination
import dvx.news.app.states.localizedIcon
import dvx.news.app.states.localizedName
import dvx.news.app.themes.DVXTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DVXBottomNavigation(
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

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = modifier
            .height(IntrinsicSize.Max)
    ) {
        for (destination in destinations) {
            val selected = destination == current

            @Composable
            fun defineSelectableColor(
                selected: Boolean,
                selectedColor: Color,
                unselectedColor: Color
            ) = animateColorAsState(
                targetValue = if (selected) selectedColor else unselectedColor,
                animationSpec = spring(stiffness = Spring.StiffnessVeryLow),
                label = "AnimatedColor"
            ).value

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .selectable(
                        selected = true,
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(false),
                        onClick = {
                            navController.navigate(destination)
                            current = destination
                        }
                    )
            ) {
                Icon(
                    painter = destination.localizedIcon,
                    contentDescription = null,
                    tint = defineSelectableColor(
                        selected = selected,
                        selectedColor = MaterialTheme.colorScheme.primary,
                        unselectedColor = MaterialTheme.colorScheme.onSurface.copy(.5f)
                    ),
                    modifier = Modifier
                        .size(32.dp)
                        .padding(bottom = 8.dp)
                )
                Text(
                    text = destination.localizedName,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center,
                    color = defineSelectableColor(
                        selected = selected,
                        selectedColor = MaterialTheme.colorScheme.onSurface,
                        unselectedColor = MaterialTheme.colorScheme.onSurface.copy(.5f)
                    )
                )
            }
        }
    }
}

@Preview
@Composable
private fun DVXBottomAppBarPreview() = DVXTheme {
    DVXBottomNavigation(
        navController = rememberNavController()
    )
}