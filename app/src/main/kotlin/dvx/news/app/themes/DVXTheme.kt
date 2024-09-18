package dvx.news.app.themes

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun DVXTheme(content: @Composable () -> Unit) {
    val colorScheme = MaterialTheme.colorScheme.copy(
        primary = primaryColor,
        onPrimary = onPrimaryColor,
        surface = surfaceColor,
        surfaceContainer = surfaceColor,
        primaryContainer = surfaceColor,
        secondary = secondaryColor,
        secondaryContainer = surfaceColor,
        tertiaryContainer = surfaceColor,
        background = Color.Gray,
    )

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

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography
    ) {
        content()
    }
}