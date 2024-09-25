package dvx.news.app.screens

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
            .height(intrinsicSize = IntrinsicSize.Min)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Nach dem Untergang seiner Luxus-Jacht",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 21.sp,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = "Die seltsamen Zufälle um den Tod des Tech-Milliardärs",
            textAlign = TextAlign.Center,
            fontFamily = openSansCondFontFamily,
            lineHeight = 44.sp,
            fontSize = 44.sp,
            fontWeight = FontWeight.ExtraBold,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(horizontal = 10.dp)
        )
        Image(
            painter = painterResource(R.drawable.img_small_preview),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 13.dp)
        )
        Text(
            text = "Beim Bau 2008 war der Mast der Superjacht mit 75 Metern der zweithöchste der Welt",
            textAlign = TextAlign.Justify,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(
                    top = 3.dp,
                    start = 20.dp,
                    end = 20.dp
                )
        )
        Text(
            text = "Foto: EPA",
            fontWeight = FontWeight.Light,
            fontSize = 24.sp,
            color = Color(0xff626362),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(
                    top = 7.dp,
                    start = 20.dp,
                    end = 20.dp
                )
                .fillMaxWidth()
        )
        Text(
            text = "Die Nacht zu Montag war lau in Porticello, einem kleinen Jachthafen an der Nordküste Siziliens.",
            textAlign = TextAlign.Justify,
            fontSize = 23.5.sp,
            lineHeight = 28.sp,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(
                    top = 22.dp,
                    start = 20.dp,
                    end = 20.dp
                )
                .fillMaxWidth()
        )
        Text(
            text = "700 Meter vor dem Hafen lag die Bayesian\" im Tyrrhenischen Meer vor Anker, eine 35- Millionen-Euro Segeljacht, die dem britischen Tech-Milliardär Mike Lynch (59) und seiner Frau Angela Bacares (57) gehört. Die Menschen an Bord, zwölf Passagiere und die zehnköpfige Besatzung, hatten etwas zu feiern.",
            textAlign = TextAlign.Justify,
            fontWeight = FontWeight.Light,
            lineHeight = 28.sp,
            fontSize = 24.sp,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(
                    top = 14.dp,
                    start = 20.dp,
                    end = 20.dp
                )
                .fillMaxWidth()
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
            fontSize = 23.sp,
            lineHeight = 28.sp,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(
                    top = 7.dp,
                    start = 20.dp,
                    end = 20.dp
                )
                .fillMaxWidth()
        )
        Text(
            text = "Foto: Uncredited/Lynch family via TANCREDI/AP",
            textAlign = TextAlign.Justify,
            fontWeight = FontWeight.Light,
            fontSize = 19.sp,
            color = Color(0xff626362),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(
                    top = 3.dp,
                    start = 20.dp,
                    end = 20.dp,
                )
                .fillMaxWidth()

        )
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Preview
@Composable
private fun ArticleScreenPreview() = DVXTheme {
    ArticleScreen()
}