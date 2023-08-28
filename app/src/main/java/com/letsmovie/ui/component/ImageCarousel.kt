package com.letsmovie.ui.component

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import com.letsmovie.model.DataListResponse
import com.letsmovie.model.Movie
import com.letsmovie.model.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@Composable
fun <T : Any> ImageCarousel(
    result: Result<DataListResponse<T>>,
    onClick: (String) -> Unit
) {
    when (result) {
        is Result.Loading -> {

        }

        is Result.Error -> {
            Log.d("ImageCarousel", "Ex: "+result.exception)
        }

        is Result.Success -> {
            ImageCarouselBody(
                listData = result.data.dataList,
                _itemNumber = 8,
                onClick = onClick
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun <T : Any> ImageCarouselBody(
    listData: List<T>,
    _itemNumber: Int,
    onClick: (String) -> Unit
) {
    val pagerState = rememberPagerState(initialPage = 0)
    val scope = rememberCoroutineScope()
    val itemNumber = if (listData.size > _itemNumber) _itemNumber else listData.size

    // Auto animate to next page
    LaunchedEffect(pagerState.settledPage) {
        delay(2000)
        val newPosition =
            if (pagerState.currentPage < itemNumber - 1) pagerState.currentPage + 1 else 0
        pagerState.animateScrollToPage(newPosition)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        HorizontalPager(
            pageCount = itemNumber,
            state = pagerState,
            pageSpacing = 10.dp,
            beyondBoundsPageCount = 2,
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
                    movieItem = currentItem as Movie,
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