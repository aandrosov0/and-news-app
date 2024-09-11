package dvx.news.app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dvx.news.app.R
import dvx.news.app.themes.DVXTheme
import dvx.news.app.themes.openSansCondFontFamily

@Composable
fun ArticleScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Nach dem Untergang seiner Luxus-Jacht",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "Die seltsamen Zufälle um den Tod des Tech-Milliardärs",
            textAlign = TextAlign.Center,
            fontFamily = openSansCondFontFamily,
            lineHeight = 62.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            style = MaterialTheme.typography.displayLarge,
        )
        Image(
            painter = painterResource(R.drawable.img_small_preview),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 13.dp,)
        )
        Text(
            text = "Beim Bau 2008 war der Mast der Superjacht mit 75 Metern der zweithöchste der Welt",
            textAlign = TextAlign.Justify,
            style = TextStyle(
                fontSize = 20.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFF18191C),
            ),
            modifier = Modifier
                .padding(
                    top = 3.dp,
                    start = 20.dp,
                    end = 20.dp
                )
        )
        Text(
            text = "Foto: EPA",
            style = TextStyle(
                fontSize = 22.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF18191C),
            ),
            modifier = Modifier
                .padding(
                    top = 7.dp,
                    start = 20.dp,
                    end = 20.dp
                )
        )
        Text(
            text = "Die Nacht zu Montag war lau in Porticello, einem kleinen Jachthafen an der Nordküste Siziliens.",
            style = TextStyle(
                fontSize = 22.sp,
                lineHeight = 38.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFF18191C),
            ),
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(
                    top = 41.dp,
                    start = 20.dp,
                    end = 20.dp
                )
        )
        Text(
            text = "700 Meter vor dem Hafen lag die Bayesian\" im Tyrrhenischen Meer vor Anker, eine 35- Millionen-Euro Segeljacht, die dem britischen Tech-Milliardär Mike Lynch (59) und seiner Frau Angela Bacares (57) gehört. Die Menschen an Bord, zwölf Passagiere und die zehnköpfige Besatzung, hatten etwas zu feiern.",
            style = TextStyle(
                fontSize = 22.sp,
                lineHeight = 38.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF18191C),
            ),
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(
                    top = 14.dp,
                    start = 20.dp,
                    end = 20.dp
                )
        )
        Image(
            painter = painterResource(R.drawable.img_large_preview),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 38.dp)
        )
        Text(
            text = "Milliardär Mike Lynch und seine Tochter Hannah ertranken, als die Jacht sank",
            textAlign = TextAlign.Justify,
            style = TextStyle(
                fontSize = 18.sp,
                lineHeight = 30.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFF18191C)
            ),
            modifier = Modifier
                .padding(
                    top = 7.dp,
                    start = 20.dp,
                    end = 20.dp
                )
        )
        Text(
            text = "Foto: Uncredited/Lynch family via TANCREDI/AP",
            textAlign = TextAlign.Justify,
            style = TextStyle(
                fontSize = 18.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF3A3A3A),
            ),
            modifier = Modifier
                .padding(
                    top = 3.dp,
                    start = 20.dp,
                    end = 20.dp
                )
        )
    }
}

@Preview
@Composable
private fun ArticleScreenPreview() = DVXTheme {
    ArticleScreen()
}