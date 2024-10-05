package dvx.news.app.themes

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import dvx.news.app.states.Theme

private val lightColorScheme = lightColorScheme(
    primary = primaryColor,
    onPrimary = onPrimaryColor,
    tertiary = tertiaryColor,
    surface = surfaceLightColor,
    onSurface = onSurfaceLightColor,
    surfaceVariant = surfaceVariantLightColor,
    onSurfaceVariant = onSurfaceVariantLightColor
)

private val darkColorScheme = darkColorScheme(
    primary = primaryColor,
    tertiary = tertiaryColor,
    surface = surfaceDarkColor,
    onSurface = onSurfaceDarkColor,
    surfaceVariant = surfaceVariantDarkColor,
    onSurfaceVariant = onSurfaceVariantDarkColor,
)

@Composable
fun DVXTheme(
    theme: Theme = Theme.SYSTEM,
    content: @Composable () -> Unit
) {
    val colorScheme = when (theme) {
        Theme.SYSTEM -> if (isSystemInDarkTheme()) darkColorScheme else lightColorScheme
        Theme.BRIGHT -> lightColorScheme
        Theme.DARK -> darkColorScheme
    }

    val typography = MaterialTheme.typography.copy(
        bodySmall = TextStyle(
            fontSize = 12.5.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = openSansCondFontFamily,
            color = Color.Black
        ),
        bodyMedium = TextStyle(
            fontSize = 20.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.W600,
            fontFamily = openSansCondFontFamily,
            color = Color.Black
        ),
        bodyLarge = TextStyle(
            fontSize = 24.sp,
            lineHeight = 27.sp,
            fontFamily = openSansCondFontFamily,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        ),
        labelMedium = TextStyle(
            color = colorScheme.primary,
            fontFamily = openSansCondFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 18.sp
        ),
        titleSmall = TextStyle(
            color = colorScheme.primary,
            fontFamily = openSansCondFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 18.sp
        ),
        titleMedium = TextStyle(
            color = colorScheme.primary,
            fontFamily = openSansCondFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            lineHeight = 24.sp
        ),
        titleLarge = TextStyle(
            color = colorScheme.primary,
            fontFamily = openSansCondFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 27.sp,
            lineHeight = 24.sp
        )
    )

    val context = LocalContext.current
    if (context is Activity) {
        val window = context.window
        val isLightTheme = colorScheme == lightColorScheme

        WindowCompat.getInsetsController(window, window.decorView)
            .isAppearanceLightStatusBars = isLightTheme
        window.statusBarColor = colorScheme.surface.toArgb()
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        content = content
    )
}