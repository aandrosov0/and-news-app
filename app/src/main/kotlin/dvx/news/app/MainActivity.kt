package dvx.news.app

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dvx.news.app.components.AppBottomBar
import dvx.news.app.screens.ArticleScreen
import dvx.news.app.screens.CategoryScreen
import dvx.news.app.screens.HomeScreen
import dvx.news.app.screens.IncludesScreen
import dvx.news.app.screens.NewsScreen
import dvx.news.app.screens.OverviewScreen
import dvx.news.app.screens.SettingsScreen
import dvx.news.app.states.Destination
import dvx.news.app.states.Settings
import dvx.news.app.states.getSavedSettings
import dvx.news.app.states.saveSettings
import dvx.news.app.themes.DVXTheme
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    private lateinit var settingsState: MutableState<Settings>

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) = runBlocking {
        super.onCreate(savedInstanceState)
        actionBar?.hide()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        settingsState = mutableStateOf(getSavedSettings())

        setContent {
            var settings by remember { settingsState }

            DVXTheme(theme = settings.theme) {
                App(
                    settings = settings,
                    onChangeSettings = { settings = it }
                )
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) = runBlocking {
        saveSettings(settingsState.value)
        super.onSaveInstanceState(outState)
    }
}

@Composable
fun App(
    settings: Settings,
    onChangeSettings: (Settings) -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    var destination: Destination by remember { mutableStateOf(Destination.Home) }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            AppBottomBar(
                destination,
                onDestinationChange = {
                    navController.navigate(it)
                    destination = it
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = destination,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable<Destination.Home> {
                HomeScreen(navController = navController)
            }
            composable<Destination.Sport> {
                CategoryScreen(
                    navController = navController,
                    category = stringResource(R.string.sport),
                )
            }
            composable<Destination.Lifestyle> {
                CategoryScreen(
                    navController = navController,
                    category = stringResource(R.string.lifestyle),
                )
            }
            composable<Destination.Entertainment> {
                CategoryScreen(
                    navController = navController,
                    category = stringResource(R.string.entertainment),
                )
            }
            composable<Destination.Menu> {
                OverviewScreen(navController = navController)
            }
            composable<Destination.Article> {
                ArticleScreen(navController = navController)
            }
            composable<Destination.News> {
                NewsScreen(navController = navController)
            }
            composable<Destination.Settings> {
                SettingsScreen(
                    navController = navController,
                    settings = settings,
                    onChangeSettings = onChangeSettings,
                )
            }
            composable<Destination.Includes> {
                IncludesScreen(navController = navController)
            }
        }
    }
}

@Preview
@Composable
private fun AppPreview() = DVXTheme {
    App(
        settings = Settings(),
        onChangeSettings = {}
    )
}