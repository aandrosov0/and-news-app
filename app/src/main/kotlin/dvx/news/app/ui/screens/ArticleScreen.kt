package dvx.news.app.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade
import dvx.news.app.R
import dvx.news.app.states.ArticleContentUiState
import dvx.news.app.states.ArticleTextTypeUiState
import dvx.news.app.themes.DVXTheme
import dvx.news.app.themes.openSansCondFontFamily
import dvx.news.app.ui.components.BackButton
import dvx.news.app.ui.components.ErrorBox
import dvx.news.app.viewModels.ArticleViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleScreen(
    id: Long,
    modifier: Modifier = Modifier,
    articleViewModel: ArticleViewModel = koinViewModel(),
    navController: NavController = rememberNavController(),
) {
    LaunchedEffect(Unit) { articleViewModel.getArticle(id) }
    val uiState by articleViewModel.uiState.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = { ArticleTopBar(onBackClick = { navController.navigateUp() }) },
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    ) { paddings ->
        PullToRefreshBox(
            isRefreshing = uiState.isLoading,
            onRefresh = { articleViewModel.getArticle(id) }
        ) {
            val error = uiState.error
            if (error != null) {
                ErrorBox(
                    icon = painterResource(error.iconId),
                    error = stringResource(error.messageId),
                    action = stringResource(error.actionId),
                    onActionClick = error.onAction,
                    modifier = modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                )
                return@PullToRefreshBox
            }

            ArticleContent(
                article = uiState.article,
                contentPadding = PaddingValues(top = 16.dp, bottom = 40.dp),
                modifier = Modifier.padding(paddings)
            )
        }
    }
}

@Composable
private fun ArticleContent(
    article: ArticleContentUiState,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues()
) {
    val text = article.text
    val images = article.images
    val content = buildList {
        var currentImageIndex = 0
        text.forEach {
            val pair = when (it.type) {
                ArticleTextTypeUiState.HEADLINE, ArticleTextTypeUiState.SUBHEADING -> {
                    currentImageIndex++
                    it to images.getOrNull(currentImageIndex-1)
                }
                else -> it to null
            }
            add(pair)
        }
    }
    LazyColumn(
        contentPadding = contentPadding,
        modifier = modifier
            .fillMaxSize()
    ) {
        items(items = content) { content ->
            val text = content.first
            val image = content.second
            when (text.type) {
                ArticleTextTypeUiState.SUBHEADLINE -> Subheadline(text = text.value)
                ArticleTextTypeUiState.HEADLINE -> Headline(text = text.value)
                ArticleTextTypeUiState.LEAD_PARAGRAPH -> LeadParagraph(text = text.value)
                ArticleTextTypeUiState.SUBHEADING -> Subheading(text = text.value)
                ArticleTextTypeUiState.PARAGRAPH -> Paragraph(text = text.value)
            }
            if (image != null) {
                Image(
                    model = image.url,
                    caption = image.caption
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ArticleTopBar(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = "Article",
                style = MaterialTheme.typography.bodyMedium,
            )
        },
        navigationIcon = {
            BackButton(
                onClick = onBackClick,
                icon = R.drawable.ic_arrow_left,
                label = "Mehr",
            )
        }
    )
}

@Composable
private fun Subheadline(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 19.sp,
        lineHeight = 28.sp,
        style = MaterialTheme.typography.titleLarge,
        modifier = modifier
            .fillMaxWidth()
    )
}

@Composable
private fun Headline(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        fontFamily = openSansCondFontFamily,
        lineHeight = 44.sp,
        fontSize = 42.sp,
        fontWeight = FontWeight.ExtraBold,
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier
            .padding(horizontal = 10.dp)
    )
}

@Composable
private fun Subheading(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        fontFamily = openSansCondFontFamily,
        lineHeight = 27.28.sp,
        fontSize = 26.04.sp,
        fontWeight = FontWeight.ExtraBold,
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 37.dp)
    )
}

@Composable
fun Image(
    model: String,
    modifier: Modifier = Modifier,
    caption: String? = null
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalPlatformContext.current)
            .data(model)
            .crossfade(true)
            .build(),
        contentDescription = null,
        modifier = modifier,
        placeholder = painterResource(R.drawable.img_small_preview),
    )

    if (caption != null) {
        Caption(text = caption)
        EPA(text = "Foto: EPA")
    }
}

@Composable
private fun Caption(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier
            .padding(
                top = 3.dp,
                start = 20.dp,
                end = 20.dp
            )
    )
}

@Composable
private fun EPA(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontWeight = FontWeight.W400,
        fontSize = 19.sp,
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier
            .padding(
                top = 3.dp,
                start = 20.dp,
                end = 20.dp
            )
            .fillMaxWidth()
            .alpha(0.64f)
    )
}

@Composable
private fun LeadParagraph(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = 19.sp,
        lineHeight = 28.sp,
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier
            .padding(
                top = 22.dp,
                start = 20.dp,
                end = 20.dp
            )
            .fillMaxWidth()
    )
}

@Composable
private fun Paragraph(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontWeight = FontWeight.W400,
        lineHeight = 28.sp,
        fontSize = 20.sp,
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier
            .padding(
                top = 14.dp,
                start = 20.dp,
                end = 20.dp
            )
            .fillMaxWidth()
    )
}

@Preview
@Composable
private fun SubheadlinePreview() {
    DVXTheme {
        Subheadline(text = "Nach dem Untergang seiner Luxus-Jacht")
    }
}

@Preview
@Composable
private fun HeadlinePreview() {
    DVXTheme {
        Headline(text = "Die seltsamen Zufälle um den Tod des Tech-\nMilliardärs")
    }
}

@Preview
@Composable
private fun SubheadingPreview() {
    DVXTheme {
        Subheading(
            text = "Die seltsamen Zufälle um den Tod des Tech-\nMilliardärs"
        )
    }
}

@Preview
@Composable
private fun ImagePreview() {
    DVXTheme {
        Image(model = "")
    }
}

@Preview
@Composable
private fun CaptionPreview() {
    DVXTheme {
        Caption(text = "Beim Bau 2008 war der Mast der Superjacht mit 75 Metern der zweithöchste der Welt")
    }
}

@Preview
@Composable
private fun EPAPreview() {
    DVXTheme {
        EPA(text = "Foto: EPA")
    }
}

@Preview
@Composable
private fun LeadParagraphPreview() {
    DVXTheme {
        LeadParagraph(text = "Die Nacht zu Montag war lau in Porticello, einem kleinen Jachthafen an der Nordküste Siziliens.")
    }
}

@Preview
@Composable
private fun ParagraphPreview() {
    DVXTheme {
        Paragraph(text = "700 Meter vor dem Hafen lag die Bayesian\" im Tyrrhenischen Meer vor Anker, eine 35- Millionen-Euro Segeljacht, die dem britischen Tech-Milliardär Mike Lynch (59) und seiner Frau Angela Bacares (57) gehört. Die Menschen an Bord, zwölf Passagiere und die zehnköpfige Besatzung, hatten etwas zu feiern.")
    }
}