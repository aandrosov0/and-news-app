package dvx.news.data.dataSources

import dvx.news.data.models.Article
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

class ArticlesOfflineDataSource : ArticlesDataSource {
    private val newsArticles = listOf(
        Article(
            id = 1L,
            subheadline = "Breaking news from the tech world",
            headline = "Tech Giants Announce Major Layoffs",
            slug = "tech-giants-layoffs",
            leadParagraph = "In a surprising turn of events, several major tech companies have announced significant layoffs, affecting thousands of employees.",
            time = "2023-10-01T08:00:00Z",
            imageUrl = "https://example.com/news-image1.jpg"
        ),
        Article(
            id = 2L,
            subheadline = "Global climate summit underway",
            headline = "World Leaders Gather for Climate Action",
            slug = "climate-action-summit",
            leadParagraph = "Leaders from around the globe are meeting to discuss urgent climate action and strategies to combat global warming.",
            time = "2023-10-02T09:30:00Z",
            imageUrl = "https://example.com/news-image2.jpg"
        ),
        Article(
            id = 3L,
            subheadline = "Sports update from the championship",
            headline = "Local Team Wins Championship Title",
            slug = "local-team-championship",
            leadParagraph = "In an exciting final match, the local team clinched the championship title, bringing joy to fans and the community.",
            time = "2023-10-03T15:00:00Z",
            imageUrl = "https://example.com/news-image3.jpg"
        ),
        Article(
            id = 4L,
            subheadline = "Health advisory issued",
            headline = "New Health Guidelines Released Amid Rising Cases",
            slug = "health-guidelines-rising-cases",
            leadParagraph = "Health officials have released new guidelines in response to a surge in cases of a contagious disease.",
            time = "2023-10-04T11:45:00Z",
            imageUrl = "https://example.com/news-image4.jpg"
        ),
        Article(
            id = 5L,
            subheadline = "Economic outlook for the coming year",
            headline = "Experts Predict Economic Recovery in 2024",
            slug = "economic-recovery-2024",
            leadParagraph = "Economists are optimistic about a potential recovery in the economy, citing various positive indicators.",
            time = "2023-10-05T14:00:00Z",
            imageUrl = "https://example.com/news-image5.jpg"
        )
    )


    override suspend fun getRecent(): List<Article> {
        delay(3.seconds)
        return newsArticles
    }

    override suspend fun getRandom(): List<Article> {
        delay(3.seconds)
        return newsArticles.shuffled()
    }
}