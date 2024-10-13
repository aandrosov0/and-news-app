package dvx.news.app.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dvx.news.app.R
import dvx.news.app.themes.DVXTheme

@Composable
fun HorizontalButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    prefixIconId: Int? = null,
    postfixIconId: Int? = null,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.primary
        ),
        shape = RectangleShape,
        contentPadding = PaddingValues(
            horizontal = 20.dp,
            vertical = 16.dp
        ),
        modifier = Modifier
    ) {
        if (prefixIconId != null) {
            Icon(
                painter = painterResource(prefixIconId),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .size(32.dp)
            )
        }
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.64f),
            fontSize = 18.sp,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .padding(start = 20.dp)
        )
        Spacer(
            modifier
                .weight(1f)
        )
        if (postfixIconId != null) {
            Icon(
                painter = painterResource(postfixIconId),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .size(16.dp)
            )
        }
    }
}

@Preview
@Composable
private fun HorizontalButtonPreview() = DVXTheme {
    HorizontalButton(
        onClick = {},
        text = "Sport",
        prefixIconId = R.drawable.ic_sport,
        postfixIconId = R.drawable.ic_down,
    )
}