package dvx.news.app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dvx.news.app.R
import dvx.news.app.themes.DVXTheme

@Composable
fun SelectableRowItem(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.Transparent)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(
                    bounded = true,
                    color = ButtonDefaults.buttonColors().containerColor
                ),
                onClick = onClick
            )
            .padding(
                horizontal = 10.dp,
                vertical = 20.dp
            )
            .clip(shape = RectangleShape)
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .alpha(.64f)
        )
        if (selected) {
            Icon(
                painter = painterResource(R.drawable.ic_tick),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(
                        width = 24.dp,
                        height = 18.dp
                    )
            )
        }
    }
}

@Preview
@Composable
private fun SelectableRowItemPreview() = DVXTheme {
    var selected by remember { mutableStateOf(true) }
    SelectableRowItem(
        text = "Schlagzeilen",
        selected = selected,
        onClick = { selected = !selected }
    )
}