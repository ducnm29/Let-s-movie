package com.letsmovie.ui.favourite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.letsmovie.ui.component.ListItemWithData
import com.letsmovie.ui.component.SearchBarUI
import com.letsmovie.ui.tv.TvViewModel

@Composable
fun FavouriteTvUI(
    modifier: Modifier = Modifier,
    tvViewModel: TvViewModel,
    onTvClick: (String) -> Unit
) {
    val trendingTvStateFlow = tvViewModel.trendingTvStateFlow.collectAsState()
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        SearchBarUI()
        ListItemWithData(
            result = trendingTvStateFlow.value,
            modifier = modifier,
            categoryName = "Trending",
            onClick = onTvClick
        )
    }
}