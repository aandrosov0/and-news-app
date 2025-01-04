package dvx.news.app.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dvx.news.app.R
import dvx.news.app.themes.DVXTheme

@Composable
fun SplashScreen(modifier: Modifier = Modifier) {
    val alphaAnimation = remember { Animatable(0f) }
    LaunchedEffect("alpha_animation") {
        alphaAnimation.animateTo(
            targetValue = 1f,
            animationSpec = tween(1000)
        )
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_logo),
            contentDescription = null,
            modifier = Modifier
                .size(192.dp)
                .graphicsLayer(alpha = alphaAnimation.value),
            tint = Color.Unspecified
        )
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    DVXTheme {
        SplashScreen()
    }
}