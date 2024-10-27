package dvx.news.app.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dvx.news.app.states.Tab
import kotlinx.coroutines.launch

@Composable
fun HorizontalTabPager(
    tabs: List<Tab>,
    initialTab: Tab,
    modifier: Modifier = Modifier,
    pagerState: PagerState = rememberPagerState(
        initialPage = tabs.indexOf(initialTab),
        pageCount = { tabs.size }
    ),
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    pageContent: @Composable PagerScope.(Tab) -> Unit
) = Column(modifier = modifier) {
    val composableCoroutine = rememberCoroutineScope()

    Tabs(
        tabs = tabs,
        currentTab = tabs[pagerState.currentPage],
        onTabSelect = { selectedTab ->
            val index = tabs.indexOf(selectedTab)
            composableCoroutine.launch {
                pagerState.animateScrollToPage(
                    index,
                    animationSpec = tween(durationMillis = 350)
                )
            }
        }
    )
    HorizontalPager(
        state = pagerState,
        verticalAlignment = verticalAlignment
    ) { page -> pageContent(tabs[page]) }
}