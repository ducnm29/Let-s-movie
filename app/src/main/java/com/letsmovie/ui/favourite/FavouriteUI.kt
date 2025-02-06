package com.letsmovie.ui.favourite

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavHostController
import com.letsmovie.R
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
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 2 })


    Column(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacer_horizontal0)))
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