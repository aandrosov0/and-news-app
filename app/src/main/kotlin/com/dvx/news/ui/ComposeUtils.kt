package com.dvx.news.ui

import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.core.view.WindowCompat

@Composable
fun StatusBarsAppearance(light: Boolean = true) {
    val activity = LocalActivity.current

    LaunchedEffect(activity) {
        val window = activity?.window

        if (window != null) {
            WindowCompat.getInsetsController(window, window.decorView)
                .isAppearanceLightStatusBars = light
        }
    }
}