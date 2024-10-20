package dvx.news.app

import android.app.Activity
import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat

fun Activity.adaptCurrentTheme(isLight: Boolean, colorScheme: ColorScheme) {
    WindowCompat.getInsetsController(window, window.decorView)
        .isAppearanceLightStatusBars = isLight
    window.statusBarColor = colorScheme.surface.toArgb()
}