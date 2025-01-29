package dvx.news.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dvx.news.app.R
import dvx.news.app.ui.themes.DVXTheme

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(Color(0xFFD30403))
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_logo_splash),
            contentDescription = stringResource(R.string.app_name),
            modifier = Modifier.size(240.dp)
        )
    }
}

@Preview
@Composable
private fun SplashScreenPreview() = DVXTheme {
    SplashScreen()
}