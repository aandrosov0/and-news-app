package com.dvx.news.ui.themes

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.dvx.news.adaptCurrentTheme
import com.dvx.news.ui.states.ThemeUiState

internal val lightColorScheme = lightColorScheme(
        primary = primaryColor,
        onPrimary = onPrimaryColor,
        tertiary = tertiaryColor,
        surface = surfaceLightColor,
        onSurface = onSurfaceLightColor,
        surfaceVariant = surfaceVariantLightColor,
        onSurfaceVariant = onSurfaceVariantLightColor
    )

internal val darkColorScheme = darkColorScheme(
        primary = primaryColor,
        onPrimary = onPrimaryColor,
        tertiary = tertiaryColor,
        surface = surfaceDarkColor,
        onSurface = onSurfaceDarkColor,
        surfaceVariant = surfaceVariantDarkColor,
        onSurfaceVariant = onSurfaceVariantDarkColor,
    )

internal val typography
    get() = Typography(
        displayLarge = TextStyle(
            fontWeight = FontWeight.Bold,
            fontFamily = openSansCondensedFontFamily
        ),
        displayMedium = TextStyle(
            fontWeight = FontWeight.Bold,
            fontFamily = openSansCondensedFontFamily
        ),
        displaySmall = TextStyle(
            fontWeight = FontWeight.Bold,
            fontFamily = openSansCondensedFontFamily
        ),
        headlineLarge = TextStyle(
            fontSize = 58.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = openSansCondensedFontFamily
        ),
        headlineMedium = TextStyle(
            lineHeight = 1.083.em,
            fontWeight = FontWeight.Bold,
            fontFamily = openSansCondensedFontFamily
        ),
        headlineSmall = TextStyle(
            fontWeight = FontWeight.Bold,
            fontFamily = openSansCondensedFontFamily
        ),
        titleLarge = TextStyle(
            color = primaryColor,
            fontWeight = FontWeight.Bold,
            fontFamily = openSansCondensedFontFamily
        ),
        titleMedium = TextStyle(
            fontSize = 20.sp,
            color = primaryColor,
            fontWeight = FontWeight.Bold,
            fontFamily = openSansCondensedFontFamily
        ),
        titleSmall = TextStyle(
            color = primaryColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = openSansCondensedFontFamily
        ),
        bodyLarge = TextStyle(
            fontWeight = FontWeight.Bold,
            fontFamily = openSansCondensedFontFamily
        ),
        bodyMedium = TextStyle(
            fontWeight = FontWeight.Bold,
            fontFamily = openSansCondensedFontFamily
        ),
        bodySmall = TextStyle(
            fontWeight = FontWeight.Bold,
            fontFamily = openSansCondensedFontFamily
        ),
        labelLarge = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = openSansCondensedFontFamily
        ),
        labelMedium = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = openSansCondensedFontFamily
        ),
        labelSmall = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 1.3.em,
            fontFamily = openSansCondensedFontFamily
        )
    )

@Composable
fun DVXTheme(
    theme: ThemeUiState = ThemeUiState.SYSTEM,
    content: @Composable () -> Unit
) {
    val colorScheme = when (theme) {
        ThemeUiState.SYSTEM -> if (isSystemInDarkTheme()) darkColorScheme else lightColorScheme
        ThemeUiState.BRIGHT -> lightColorScheme
        ThemeUiState.DARK -> darkColorScheme
    }

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

