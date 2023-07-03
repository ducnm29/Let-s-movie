package com.letsmovie.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.letsmovie.ui.favourite.FavouriteMovieUI
import com.letsmovie.ui.favourite.FavouriteTvUI
import com.letsmovie.ui.movie.MovieUI
import com.letsmovie.ui.movie.MovieViewModel
import com.letsmovie.ui.tv.TvViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabContent(
    pagerState: PagerState,
    navHostController: NavHostController,
    movieViewModel: MovieViewModel,
    tvViewModel: TvViewModel
) {
    HorizontalPager(
        state = pagerState,
        pageCount = 2
    ) {page ->
        when(page){
            0 -> FavouriteMovieUI(
               navHostController = navHostController,
               movieViewModel = movieViewModel)

            1 -> FavouriteTvUI(
                navHostController = navHostController,
                tvViewModel = tvViewModel
            )
        }
    }
}