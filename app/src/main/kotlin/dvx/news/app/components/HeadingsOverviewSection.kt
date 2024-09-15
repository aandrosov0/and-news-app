package dvx.news.app.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dvx.news.app.R
import dvx.news.app.themes.DVXTheme

@Composable
fun HeadingsOverviewSection(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "RUBRIKEN",
            fontSize = 26.sp,
            modifier = Modifier.padding(
                start = 10.dp,
                end = 10.dp,
                bottom = 8.dp,
                top = 18.dp
            )
        )
        HorizontalDivider()
        HorizontalButton(
            onClick = {},
            text = "Sport",
            prefixIconId = R.drawable.ic_sport,
            postfixIconId = R.drawable.ic_down
        )
        HorizontalDivider()
        HorizontalButton(
            onClick = {},
            text = "Lifestyle",
            prefixIconId = R.drawable.ic_lifestyle,
            postfixIconId = R.drawable.ic_down
        )
        HorizontalDivider()
        HorizontalButton(
            onClick = {},
            text = "Unterhaltung",
            prefixIconId = R.drawable.ic_unterhaltung,
            postfixIconId = R.drawable.ic_down
        )
        HorizontalDivider()
    }
}

@Preview(showBackground = true)
@Composable
private fun HeadingsOverviewSectionPreview() = DVXTheme {
    HeadingsOverviewSection()
}