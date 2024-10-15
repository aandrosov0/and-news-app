package dvx.news.app.components.sections

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
import dvx.news.app.components.HorizontalButton
import dvx.news.app.themes.DVXTheme

@Composable
fun OverviewTopicsSection(
    onNews: () -> Unit,
    onIncludes: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(top = 28.dp)
    ) {
        Text(
            text = "TOP-THEMEN",
            fontSize = 26.sp,
            modifier = Modifier.padding(
                start = 10.dp,
                end = 10.dp,
                bottom = 8.dp
            )
        )
        HorizontalDivider()
        HorizontalButton(
            onClick = {},
            text = "Schlagzeilen",
            prefixIconId = R.drawable.ic_schlagzeilen,
        )
        HorizontalDivider()
        HorizontalButton(
            onClick = onNews,
            text = "Newsticker",
            prefixIconId = R.drawable.ic_schlagzeilen,
        )
        HorizontalDivider()
        HorizontalButton(
            onClick = onIncludes,
            text = "Includes",
            prefixIconId = R.drawable.ic_schlagzeilen,
        )
        HorizontalDivider()
    }
}

@Preview(showBackground = true)
@Composable
private fun TopTopicsOverviewSectionPreview() = DVXTheme {
    OverviewTopicsSection(
        onNews = {},
        onIncludes = {}
    )
}

