package dvx.news.app.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dvx.news.app.themes.DVXTheme
import dvx.news.app.R

@Composable
fun BackButton(
    onClick: () -> Unit,
    icon: Int,
    modifier: Modifier = Modifier,
    label: String? = null
) {
    TextButton(
        onClick = onClick,
        contentPadding = PaddingValues(8.dp),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .size(31.dp)
        )
        if (!label.isNullOrBlank()) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Preview
@Composable
private fun HorizontalButtonPreview() = DVXTheme {
    BackButton(
        onClick = {},
        icon = R.drawable.ic_arrow_left,
        label = stringResource(R.string.menu)
    )
}