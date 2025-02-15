package dvx.news.app.ui.themes

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import dvx.news.app.R

internal val openSansCondensedFontFamily
    get() = FontFamily(
        Font(R.font.open_sans_condensed_light, FontWeight.Light),
        Font(R.font.open_sans_condensed_light_italic, FontWeight.Light, FontStyle.Italic),
        Font(R.font.open_sans_condensed_regular, FontWeight.Normal),
        Font(R.font.open_sans_condensed_italic, FontWeight.Normal, FontStyle.Italic),
        Font(R.font.open_sans_condensed_medium, FontWeight.Medium),
        Font(R.font.open_sans_condensed_medium_italic, FontWeight.Medium, FontStyle.Italic),
        Font(R.font.open_sans_condensed_semibold, FontWeight.SemiBold),
        Font(R.font.open_sans_condensed_semibold_italic, FontWeight.SemiBold, FontStyle.Italic),
        Font(R.font.open_sans_condensed_bold, FontWeight.Bold),
        Font(R.font.open_sans_condensed_bold_italic, FontWeight.Bold, FontStyle.Italic),
        Font(R.font.open_sans_condensed_extra_bold, FontWeight.ExtraBold),
        Font(R.font.open_sans_condensed_extra_bold_italic, FontWeight.ExtraBold, FontStyle.Italic),
    )