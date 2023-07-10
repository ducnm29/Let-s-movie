package com.letsmovie.ui.favourite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.letsmovie.ui.component.SearchBarUI
import com.letsmovie.ui.component.ListItemWithData
import com.letsmovie.ui.navigation.BaseScreen
import com.letsmovie.ui.tv.TvViewModel

@Composable
fun FavouriteTvUI(
    modifier: Modifier = Modifier,
    tvViewModel: TvViewModel,
    navHostController: NavHostController
) {
    val trendingTvStateFlow = tvViewModel.trendingTvStateFlow.collectAsState()
    LaunchedEffect(true){
        tvViewModel.getTrendingTv()
    }
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        SearchBarUI()
        ListItemWithData(
            result =  trendingTvStateFlow.value,
            modifier = modifier,
            navHostController = navHostController,
            categoryName = "Trending",
            onClick = {tvId ->
                navHostController.navigate(BaseScreen.TvDetailScreen.route + "/"+tvId)
            }
        )
    }
}