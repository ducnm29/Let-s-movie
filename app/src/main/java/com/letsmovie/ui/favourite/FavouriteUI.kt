package com.letsmovie.ui.favourite

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.letsmovie.model.TagIcon
import com.letsmovie.ui.component.TabContent
import com.letsmovie.ui.component.Tabs
import com.letsmovie.ui.movie.MovieViewModel
import com.letsmovie.ui.tv.TvViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavouriteUI(
    modifier: Modifier = Modifier,
    movieViewModel: MovieViewModel,
    tvViewModel: TvViewModel,
    onMovieClick: (String) -> Unit,
    onTvClick: (String) -> Unit
) {
    val pagerState = rememberPagerState(initialPage = 0)


    Column(
        modifier = modifier
    ) {
        Tabs(
            pagerState = pagerState,
            listTabs = listOf(
                TagIcon("Movie", Icons.Default.AccountBox),
                TagIcon("Tv", Icons.Default.AccountBox)
            )
        )
        TabContent(
            pagerState = pagerState,
            movieViewModel = movieViewModel,
            tvViewModel = tvViewModel,
            onMovieClick = onMovieClick,
            onTvClick = onTvClick
        )
    }
}