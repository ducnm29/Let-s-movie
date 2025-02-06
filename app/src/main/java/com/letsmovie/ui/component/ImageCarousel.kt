package com.letsmovie.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.letsmovie.model.Movie
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@Composable
fun MovieCarousel(
    modifier: Modifier = Modifier,
    listMovie: List<Movie>,
    onClick: (String) -> Unit
) {
    ImageCarouselBody(
        modifier = modifier,
        listData = listMovie,
        _itemNumber = 8,
        onClick = onClick
    )
}

@Composable
fun ImageCarouselBody(
    modifier: Modifier = Modifier,
    listData: List<Movie>,
    _itemNumber: Int,
    onClick: (String) -> Unit
) {
    val scope = rememberCoroutineScope()
    val itemNumber = if (listData.size > _itemNumber) _itemNumber else listData.size
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { itemNumber })

    // Auto animate to next page
    LaunchedEffect(pagerState.settledPage, itemNumber) {
        delay(2000)
        val newPosition =
            if (pagerState.currentPage < itemNumber - 1) pagerState.currentPage + 1 else 0
        pagerState.animateScrollToPage(newPosition)
    }
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        HorizontalPager(
            state = pagerState,
            pageSpacing = 10.dp,
            contentPadding = PaddingValues(16.dp)
        ) { index ->
            Surface(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .graphicsLayer {
                        val pageOffset = ((pagerState.currentPage - index) + pagerState
                            .currentPageOffsetFraction).absoluteValue
                        alpha = lerp(
                            start = 0.4f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                        translationY = lerp(
                            start = 0.dp.toPx(),
                            stop = 10.dp.toPx(),
                            fraction = pageOffset.coerceIn(0f, 1f)
                        )
                    }
            ) {
                val currentItem = listData[index]
                CarouselItem(
                    movieItem = currentItem,
                    onClick = onClick
                )
            }
        }
        Row(
            modifier = Modifier
                .height(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(itemNumber) { index ->
                val color = if (pagerState.currentPage == index) Color.DarkGray else Color.LightGray
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(10.dp)
                        .clickable {
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                )
            }
        }
    }
}