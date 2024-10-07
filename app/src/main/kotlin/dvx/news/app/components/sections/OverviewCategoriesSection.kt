package dvx.news.app.components.sections

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dvx.news.app.R
import dvx.news.app.components.HorizontalButton
import dvx.news.app.themes.DVXTheme

@Composable
fun OverviewCategoriesSection(
    onSport: () -> Unit,
    onLifestyle: () -> Unit,
    onEntertainment: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.topics),
            fontSize = 26.sp,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(
                start = 10.dp,
                end = 10.dp,
                bottom = 8.dp,
                top = 18.dp
            )
        )
        HorizontalDivider()
        HorizontalButton(
            onClick = onSport,
            text = stringResource(R.string.sport),
            prefixIconId = R.drawable.ic_sport,
            postfixIconId = R.drawable.ic_down
        )
        HorizontalDivider()
        HorizontalButton(
            onClick = onLifestyle,
            text = stringResource(R.string.lifestyle),
            prefixIconId = R.drawable.ic_lifestyle,
            postfixIconId = R.drawable.ic_down
        )
        HorizontalDivider()
        HorizontalButton(
            onClick = onEntertainment,
            text = stringResource(R.string.entertainment),
            prefixIconId = R.drawable.ic_unterhaltung,
            postfixIconId = R.drawable.ic_down
        )
        HorizontalDivider()
    }
}

@Preview(showBackground = true)
@Composable
private fun HeadingsOverviewSectionPreview() = DVXTheme {
    OverviewCategoriesSection(
        onEntertainment = {},
        onSport = {},
        onLifestyle = {}
    )
}