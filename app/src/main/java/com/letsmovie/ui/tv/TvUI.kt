package com.letsmovie.ui.tv

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.letsmovie.ui.component.HeaderUserInfoUI
import com.letsmovie.ui.component.SearchBarUI
import com.letsmovie.ui.component.ListItemWithData
import com.letsmovie.ui.navigation.BaseScreen

@Composable
fun TvUI(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    tvViewModel: TvViewModel
) {
    val trendingTvResult = tvViewModel.trendingTvStateFlow.collectAsState()
    val popularTvResult = tvViewModel.popularTvStateFlow.collectAsState()
    LaunchedEffect(true){
        tvViewModel.getTrendingTv()
        tvViewModel.getPopularTv()
    }
    Column(
        modifier = Modifier.
        verticalScroll(rememberScrollState())
    ) {
        HeaderUserInfoUI()
        SearchBarUI()
        ListItemWithData(
            result =  trendingTvResult.value,
            modifier = modifier,
            navHostController = navHostController,
            categoryName = "Trending",
            onClick = {tvId ->
                navHostController.navigate(BaseScreen.TvDetailScreen.route + "/"+tvId)
            }
        )
        ListItemWithData(
            result =  popularTvResult.value,
            modifier = modifier,
            navHostController = navHostController,
            categoryName = "Popular",
            onClick = {tvId ->
                navHostController.navigate(BaseScreen.TvDetailScreen.route + "/"+tvId)
            }
        )
    }
}