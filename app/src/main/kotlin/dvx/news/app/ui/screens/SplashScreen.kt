package dvx.news.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dvx.news.app.R
import dvx.news.app.ui.states.ErrorUiState
import dvx.news.app.ui.themes.DVXTheme

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    error: ErrorUiState? = null,
    onRetryClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .background(Color(0xFFD30403))
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.ic_logo_splash),
            contentDescription = stringResource(R.string.app_name),
            modifier = Modifier.size(280.dp)
        )

        val bottomElementModifier = Modifier
            .padding(bottom = 146.dp)
            .align(Alignment.BottomCenter)

        if (error != null) {
            Error(
                text = stringResource(error.messageId),
                onRetryClick = onRetryClick,
                modifier = bottomElementModifier
            )
            return
        }
    }
}

@Composable
private fun Error(
    text: String,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
        RetryButton(onClick = onRetryClick)
    }
}

@Composable
private fun RetryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.textButtonColors(
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        contentPadding = PaddingValues()
    ) {
        Text(
            text = stringResource(R.string.retry),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
private fun SplashScreenPreview() = DVXTheme {
    SplashScreen()
}

@Preview
@Composable
private fun ErrorPreview() = DVXTheme {
    Error(
        text = "Something went wrong...",
        onRetryClick = {}
    )
}

@Preview
@Composable
fun RetryButtonPreview() = DVXTheme {
    RetryButton(onClick = {})
}