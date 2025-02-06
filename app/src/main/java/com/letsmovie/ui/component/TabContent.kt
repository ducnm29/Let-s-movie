package com.letsmovie.ui.component

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import com.letsmovie.ui.favourite.FavouriteMovieUI
import com.letsmovie.ui.favourite.FavouriteTvUI
import com.letsmovie.ui.movie.MovieViewModel
import com.letsmovie.ui.tv.TvViewModel

@Composable
fun TabContent(
    pagerState: PagerState,
    movieViewModel: MovieViewModel,
    tvViewModel: TvViewModel,
    onMovieClick: (String) -> Unit,
    onTvClick: (String) -> Unit
) {
    HorizontalPager(
        state = pagerState
    ) { page ->
        when (page) {
            0 -> FavouriteMovieUI(
                movieViewModel = movieViewModel,
                onMovieClick = onMovieClick
            )

            1 -> FavouriteTvUI(
                tvViewModel = tvViewModel,
                onTvClick = onTvClick
            )
        }
    }
}