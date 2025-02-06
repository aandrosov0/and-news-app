package dvx.news.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import dvx.news.app.R
import dvx.news.app.ui.components.DVXTopAppBar
import dvx.news.app.ui.components.HorizontalPost
import dvx.news.app.ui.components.Screen
import dvx.news.app.ui.components.VerticalPost
import dvx.news.app.ui.screens.navigation.Article
import dvx.news.app.ui.states.ArticleContentElementUiState
import dvx.news.app.ui.states.ArticleContentImageUiState
import dvx.news.app.ui.states.ArticleContentTextUiState
import dvx.news.app.ui.states.ArticleContentUiState
import dvx.news.app.ui.states.ArticleTextTypeUiState
import dvx.news.app.ui.states.ArticleUiState
import dvx.news.app.ui.states.RefreshableScreenState
import dvx.news.app.ui.themes.DVXTheme
import dvx.news.app.ui.themes.openSansCondFontFamily
import dvx.news.app.ui.viewModels.ArticleViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ArticleScreen(
    id: Long,
    navController: NavController,
    modifier: Modifier = Modifier,
    articleViewModel: ArticleViewModel = koinViewModel(),
) {
    LaunchedEffect(Unit) { articleViewModel.getArticle(id) }
    val uiState by articleViewModel.uiState.collectAsState()

    Screen(
        modifier = modifier,
        state = RefreshableScreenState(
            error = uiState.error,
            isRefreshing = uiState.isLoading,
            onRefresh = { articleViewModel.getArticle(id, refresh = true) }
        ),
        topBar = { DVXTopAppBar(onBackClick = navController::navigateUp) },
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Content(
            article = uiState.article,
            onArticleClick = { navController.navigate(Article(id = it.id)) },
            recommendedEndBlock = uiState.recommendedEndBlock,
            recommendedMiddleBlock = uiState.recommendedMiddleBlock,
            contentPadding = PaddingValues(top = 16.dp, bottom = 40.dp)
        )
    }
}

@Composable
private fun Content(
    article: ArticleContentUiState,
    onArticleClick: (ArticleUiState) -> Unit,
    recommendedMiddleBlock: List<ArticleUiState>,
    recommendedEndBlock: List<ArticleUiState>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues()
) {
    LazyColumn(
        contentPadding = contentPadding,
        modifier = modifier
            .fillMaxSize()
    ) {
        itemsIndexed(items = article.elements) { index, element ->
            ArticleElement(element)

            if (index == article.elements.size / 2) {
                MiddleRecommendations(
                    articles = recommendedMiddleBlock,
                    onArticleClick = onArticleClick,
                )
            }
        }
        item {
            if (recommendedEndBlock.isNotEmpty()) {
                EndRecommendations(
                    articles = recommendedEndBlock,
                    onArticleClick = onArticleClick,
                )
            }
        }
    }
}

@Composable
fun ArticleElement(
    element: ArticleContentElementUiState,
    modifier: Modifier = Modifier
) {
    when (element) {
        is ArticleContentImageUiState -> Image(
            model = element.url,
            caption = element.caption,
            modifier = modifier,
        )
        is ArticleContentTextUiState -> {
            val modifierPaddings = modifier.padding(horizontal = 10.dp)
            when (element.type) {
                ArticleTextTypeUiState.SUBHEADLINE -> Subheadline(
                    text = element.value,
                    modifier = modifierPaddings
                )
                ArticleTextTypeUiState.HEADLINE -> Headline(
                    text = element.value,
                    modifier = modifierPaddings
                )
                ArticleTextTypeUiState.LEAD_PARAGRAPH -> LeadParagraph(
                    text = element.value,
                    modifier = modifierPaddings
                )
                ArticleTextTypeUiState.SUBHEADING -> Subheading(
                    text = element.value,
                    modifier = modifierPaddings
                )
                ArticleTextTypeUiState.PARAGRAPH -> Paragraph(
                    text = element.value,
                    modifier = modifierPaddings
                )
            }
        }
    }
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
        letterSpacing = (-0.5).sp,
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
        lineHeight = 1.em,
        fontSize = 42.sp,
        letterSpacing = (-2).sp,
        fontWeight = FontWeight.ExtraBold,
        style = MaterialTheme.typography.bodyLarge.copy(
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            )
        ),
        modifier = modifier
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
            .padding(top = 37.dp)
    )
}

@Composable
private fun Image(
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
        modifier = modifier.fillMaxWidth(),
        placeholder = painterResource(R.drawable.img_rectangle_preview),
        contentScale = ContentScale.FillWidth
    )

    if (caption != null) {
        Caption(text = caption, modifier = Modifier.padding(horizontal = 10.dp))
        EPA(text = "Foto: EPA", modifier = Modifier.padding(horizontal = 10.dp))
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
            .padding(top = 22.dp)
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
            .padding(top = 14.dp)
            .fillMaxWidth()
    )
}

@Composable
private fun MiddleRecommendations(
    articles: List<ArticleUiState>,
    onArticleClick: (ArticleUiState) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier.padding(vertical = 25.dp),
        verticalArrangement = Arrangement.spacedBy(9.dp)
    ) {
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
        )
        Column(
            modifier = Modifier.padding(horizontal = 40.dp),
            verticalArrangement = Arrangement.spacedBy(14.22.dp)
        ) {
            articles.forEach { article ->
                val painter = if (LocalInspectionMode.current) {
                    painterResource(R.drawable.ic_void)
                } else {
                    rememberAsyncImagePainter(article.imageUrl)
                }
                HorizontalPost(
                    image = painter,
                    headline = article.headline,
                    subheadline = article.subheadline,
                    onClick = { onArticleClick(article) },
                    modifier = modifier.fillMaxWidth()
                )
            }
        }
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
        )
    }
}

@Composable
private fun EndRecommendations(
    articles: List<ArticleUiState>,
    onArticleClick: (ArticleUiState) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Subheading(
            text = "MEHR AUS DEM NETZ",
            modifier = Modifier
                .width(IntrinsicSize.Max)
                .padding(horizontal = 10.dp)
        )
        articles.forEach { article ->
            val painter = if (LocalInspectionMode.current) {
                painterResource(R.drawable.img_rectangle_preview)
            } else {
                rememberAsyncImagePainter(article.imageUrl)
            }
            VerticalPost(
                image = painter,
                subheadline = article.subheadline,
                headline = article.headline,
                onClick = { onArticleClick(article) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            )
        }
    }
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

@Preview
@Composable
private fun MiddleRecommendationsPreview() {
    DVXTheme {
        MiddleRecommendations(
            articles = listOf(
                ArticleUiState(
                    headline = "Headline",
                    subheadline = "Subheadline"
                ),
                ArticleUiState(
                    headline = "Headline",
                    subheadline = "Subheadline"
                ),
            ),
            onArticleClick = {}
        )
    }
}

@Preview
@Composable
private fun EndRecommendationsPreview() {
    DVXTheme {
        EndRecommendations(
            articles = listOf(
                ArticleUiState(
                    headline = "Headline",
                    subheadline = "Subheadline"
                ),
                ArticleUiState(
                    headline = "Headline",
                    subheadline = "Subheadline"
                ),
            ),
            onArticleClick = {}
        )
    }
}