package dvx.news.app.ui.themes

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dvx.news.app.adaptCurrentTheme
import dvx.news.app.ui.states.Theme

val lightColorScheme = lightColorScheme(
    primary = primaryColor,
    onPrimary = onPrimaryColor,
    tertiary = tertiaryColor,
    surface = surfaceLightColor,
    onSurface = onSurfaceLightColor,
    surfaceVariant = surfaceVariantLightColor,
    onSurfaceVariant = onSurfaceVariantLightColor
)

val darkColorScheme = darkColorScheme(
    primary = primaryColor,
    onPrimary = onPrimaryColor,
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
            color = colorScheme.onSurface
        ),
        bodyMedium = TextStyle(
            color = colorScheme.onSurface,
            fontSize = 20.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.W600,
            fontFamily = openSansCondFontFamily
        ),
        bodyLarge = TextStyle(
            fontSize = 24.sp,
            lineHeight = 27.sp,
            fontFamily = openSansCondFontFamily,
            fontWeight = FontWeight.Bold,
            color = colorScheme.onSurface
        ),
        labelMedium = TextStyle(
            fontFamily = openSansCondFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 18.sp,
            color = colorScheme.primary,
        ),
        titleSmall = TextStyle(
            fontFamily = openSansCondFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 18.sp,
            color = colorScheme.primary
        ),
        titleMedium = TextStyle(
            fontFamily = openSansCondFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            lineHeight = 24.sp,
            color = colorScheme.primary
        ),
        titleLarge = TextStyle(
            fontSize = 27.sp,
            fontFamily = openSansCondFontFamily,
            fontWeight = FontWeight.Bold,
            lineHeight = 24.sp,
            color = colorScheme.primary
        ),
        headlineSmall = MaterialTheme.typography.headlineSmall.copy(
            fontFamily = openSansCondFontFamily,
            fontWeight = FontWeight.Bold,
        ),
        headlineMedium = MaterialTheme.typography.headlineMedium.copy(
            fontFamily = openSansCondFontFamily,
            fontWeight = FontWeight.Bold,
        ),
        headlineLarge = MaterialTheme.typography.headlineLarge.copy(
            fontFamily = openSansCondFontFamily,
            fontWeight = FontWeight.Bold,
        )
    )

    val context = LocalContext.current
    if (context is Activity) {
        context.adaptCurrentTheme(colorScheme == lightColorScheme)
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        content = content
    )
}

