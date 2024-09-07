package dvx.news.app.themes

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun DVXTheme(content: @Composable () -> Unit) {
    val colorScheme = MaterialTheme.colorScheme.copy(
        primary = primaryColor,
    )

    val typography = MaterialTheme.typography.copy(
        bodyMedium = TextStyle(
            fontSize = 20.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.W600,
            fontFamily = openSansCondFontFamily
        ),
        bodyLarge = TextStyle(
            fontSize = 26.sp,
            lineHeight = 27.sp,
            fontFamily = openSansCondFontFamily,
            fontWeight = FontWeight.Bold
        ),
        titleSmall = TextStyle(
            color = colorScheme.primary,
            fontFamily = openSansCondFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 18.sp
        )
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography
    ) {
        content()
    }
}