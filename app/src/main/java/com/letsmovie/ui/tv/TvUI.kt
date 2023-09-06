package com.letsmovie.ui.tv

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.letsmovie.R
import com.letsmovie.ui.component.HeaderUserInfoUI
import com.letsmovie.ui.component.ListItemWithData
import com.letsmovie.ui.component.SearchBarUI

@Composable
fun TvUI(
    modifier: Modifier = Modifier,
    tvViewModel: TvViewModel,
    onTvClick: (String) -> Unit
) {
    val trendingTvResult = tvViewModel.trendingTvStateFlow.collectAsState()
    val popularTvResult = tvViewModel.popularTvStateFlow.collectAsState()

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacer_horizontal0)))
        HeaderUserInfoUI()
        SearchBarUI(onClick = {})
        ListItemWithData(
            result = trendingTvResult.value,
            modifier = modifier,
            categoryName = stringResource(id = R.string.trending_title),
            onClick = onTvClick
        )
        ListItemWithData(
            result = popularTvResult.value,
            modifier = modifier,
            categoryName = stringResource(id = R.string.popular_title),
            onClick = onTvClick
        )
    }
}