package com.letsmovie.ui.favourite

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.letsmovie.model.TagIcon
import com.letsmovie.ui.component.TabContent
import com.letsmovie.ui.component.Tabs
import com.letsmovie.ui.movie.MovieViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavouriteUI(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    movieViewModel: MovieViewModel
) {
    val pagerState = rememberPagerState(initialPage = 0)


    Column(
        modifier = Modifier
    ) {
        Tabs(
            pagerState = pagerState,
            listTabs = listOf(
                TagIcon("Movie", Icons.Default.AccountBox),
                TagIcon("TV", Icons.Default.AccountBox)
            )
        )
        TabContent(
            pagerState = pagerState,
            navHostController = navHostController,
            movieViewModel = movieViewModel)
    }
}