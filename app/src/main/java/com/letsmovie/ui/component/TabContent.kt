package com.letsmovie.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.letsmovie.ui.movie.MovieUI
import com.letsmovie.ui.movie.MovieViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabContent(
    pagerState: PagerState,
    navHostController: NavHostController,
    movieViewModel: MovieViewModel
) {
    HorizontalPager(
        state = pagerState,
        pageCount = 2
    ) {page ->
        when(page){
            0 -> MovieUI(
               navHostController = navHostController,
               movieViewModel = movieViewModel)

            1 -> MovieUI(
                navHostController = navHostController,
                movieViewModel = movieViewModel
            )
        }
    }
}