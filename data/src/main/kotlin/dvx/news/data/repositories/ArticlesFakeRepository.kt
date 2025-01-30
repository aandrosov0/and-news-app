package dvx.news.data.repositories

import dvx.news.data.models.Article
import dvx.news.data.models.ArticleContent
import dvx.news.data.models.ArticleContentImage
import dvx.news.data.models.ArticleContentText
import dvx.news.data.models.ArticleContentTextType
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

class ArticlesFakeRepository : ArticlesRepository {
    companion object {
        private const val DELAY = 0.5
    }

    private val articles = listOf(
        Article(
            id = 1,
            categoryId = 30,
            subheadline = "Global leaders meet to discuss climate change",
            headline = "Climate Summit 2023: A Call to Action",
            slug = "climate-summit-2023",
            leadParagraph = "World leaders gather in New York to address the urgent climate crisis.",
            time = "2023-10-01 09:00:00",
            imageUrl = "https://cdn.vox-cdn.com/thumbor/M5p0PraMoMZGpqjMk2uJW5uoVTM=/0x0:6720x4480/1200x800/filters:focal(2823x1703:3897x2777)/cdn.vox-cdn.com/uploads/chorus_image/image/65304903/1176587267.jpg.0.jpg"
        ),
        Article(
            id = 2,
            categoryId = 52,
            subheadline = "New technology promises to revolutionize healthcare",
            headline = "AI in Medicine: The Future of Healthcare",
            slug = "ai-in-medicine",
            leadParagraph = "Artificial intelligence is set to transform patient care and diagnostics.",
            time = "2023-10-02 10:30:00",
            imageUrl = "https://www.healthcareitnews.com/sites/hitnai/files/GettyImages-1066949512_1.jpg"
        ),
        Article(
            id = 3,
            categoryId = 30,
            subheadline = "Stock markets react to economic forecasts",
            headline = "Market Volatility: What Investors Need to Know",
            slug = "market-volatility",
            leadParagraph = "Investors are on edge as economic indicators show mixed signals.",
            time = "2023-10-03 11:15:00",
            imageUrl = "https://www.bankrate.com/2022/08/24115227/what-is-market-volatility.jpeg"
        ),
        Article(
            id = 4,
            categoryId = 30,
            subheadline = "New legislation aims to improve education",
            headline = "Education Reform: A Step Towards Equity",
            slug = "education-reform",
            leadParagraph = "The government introduces new policies to ensure equal access to education.",
            time = "2023-10-04 12:00:00",
            imageUrl = "https://www.letsgolearn.com/wp-content/uploads/2021/04/7171827696_182f40822f_b.jpg"
        ),
        Article(
            id = 5,
            categoryId = 52,
            subheadline = "Sports teams prepare for the upcoming season",
            headline = "2023 Sports Season: What to Expect",
            slug = "2023-sports-season",
            leadParagraph = "Teams across the league are gearing up for an exciting new season.",
            time = "2023-10-05 13:45:00",
            imageUrl = "https://sportblurb.com/wp-content/uploads/2021/12/Custom-dimensions-900x500-px-7-1-1-768x427.jpeg"
        ),
        Article(
            id = 6,
            categoryId = 18,
            subheadline = "New discoveries in space exploration",
            headline = "NASA's Latest Mission: Exploring Mars",
            slug = "nasa-mars-mission",
            leadParagraph = "NASA announces new findings from its ongoing Mars exploration mission.",
            time = "2023-10-06 14:30:00",
            imageUrl = "https://cdn.mos.cms.futurecdn.net/Phxj4Z77PkqMRXEsknUJFQ.jpg"
        ),
        Article(
            id = 7,
            categoryId = 18,
            subheadline = "Local community rallies for a cause",
            headline = "Community Unites for Environmental Cleanup",
            slug = "community-cleanup",
            leadParagraph = "Residents come together to clean up local parks and waterways.",
            time = "2023-10-07 15:15:00",
            imageUrl = "https://www.budgetdumpster.com/blog/wp-content/uploads/2016/11/litter-cleanup.jpg"
        ),
        Article(
            id = 8,
            categoryId = 52,
            subheadline = "New trends in the tech industry",
            headline = "Tech Innovations: What's Next?",
            slug = "tech-innovations",
            leadParagraph = "Experts discuss the latest trends shaping the future of technology.",
            time = "2023-10-08 16:00:00",
            imageUrl = "https://aijournalism.net/wp-content/uploads/2021/10/getty_493339904_210896.jpg"
        ),
        Article(
            id = 9,
            categoryId = 18,
            subheadline = "Health officials warn about flu season",
            headline = "Flu Season 2023: What You Need to Know",
            slug = "flu-season-2023",
            leadParagraph = "Health experts advise on vaccinations and preventive measures.",
            time = "2023-10-09 17:45:00",
            imageUrl = "https://cdn.powerofpositivity.com/wp-content/uploads/2020/12/flu-season-survival-canva.jpg"
        ),
        Article(
            id = 10,
            categoryId = 52,
            subheadline = "Cultural festival celebrates diversity",
            headline = "Annual Cultural Festival: A Celebration of Unity",
            slug = "cultural-festival-2023",
            leadParagraph = "The city hosts its",
            time = "2023-10-10 18:30:00",
            imageUrl = "https://www.tripfore.com/wp-content/uploads/2019/11/13.-Readers-Digest-1536x1024.jpg"
        )
    )

    override suspend fun getRecent(refresh: Boolean): List<Article> {
        delay(DELAY.seconds)
        return articles
    }

    override suspend fun getRandom(refresh: Boolean): List<Article> {
        delay(DELAY.seconds)
        return articles
    }

    override suspend fun getArticle(articleId: Long): ArticleContent {
        delay(DELAY.seconds)
        return ArticleContent(
            id = articleId,
            elements = listOf(
                ArticleContentText(
                    id = 1,
                    type = ArticleContentTextType.HEADLINE,
                    value = "Breaking News: Kotlin is Awesome!"
                ),
                ArticleContentText(
                    id = 2,
                    type = ArticleContentTextType.SUBHEADLINE,
                    value = "A deep dive into the features of Kotlin."
                ),
                ArticleContentImage(
                    id = 1,
                    type = 1,
                    url = "https://img.ifunny.co/images/e1362f2cac71780ec90869f3fb18a33a9a60d2c23f8f351cc6dfb4fc449cbc73_1.jpg",
                    caption = "Kotlin logo"
                ),
                ArticleContentText(
                    id = 3,
                    type = ArticleContentTextType.LEAD_PARAGRAPH,
                    value = "Kotlin has gained immense popularity among developers for its concise syntax and powerful features."
                ),
                ArticleContentText(
                    id = 4,
                    type = ArticleContentTextType.PARAGRAPH,
                    value = "In this article, we will explore the various features that make Kotlin a preferred choice for modern development."
                )
            )
        )
    }

    override suspend fun getByCategory(categoryId: Long): List<Article> {
        delay(3.seconds)
        return articles
    }
}