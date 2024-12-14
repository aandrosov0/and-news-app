package dvx.news.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dvx.news.app.themes.DVXTheme
import dvx.news.app.viewModels.HomeViewModel
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import dvx.news.app.androidModule
import dvx.news.app.components.HomeArticleImage
import dvx.news.data.dataOfflineModule
import org.koin.compose.KoinApplication

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = koinViewModel()
) {
    LaunchedEffect(Unit) {
        homeViewModel.getArticles()
    }

    val homeArticles by homeViewModel.uiState.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        if (homeArticles.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            Column(
                verticalArrangement = Arrangement.spacedBy(14.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .verticalScroll(rememberScrollState())
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .fillMaxSize()
            ) {
                for(article in homeArticles.articles) {
                    HomeArticleImage(
                        imageUrl = article.imageUrl,
                        onClick = { TODO("navigate to article") }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() = DVXTheme {
    KoinApplication(
        application = { modules(androidModule, dataOfflineModule) }
    ) {
        HomeScreen(
            navController = rememberNavController()
        )
    }
}