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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
    val uiState = tvViewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacer_horizontal0)))
        HeaderUserInfoUI()
        SearchBarUI(onClick = {})
        ListItemTV(
            listItem = uiState.value.listTrendingTv,
            modifier = Modifier.padding(top = 16.dp),
            listName = stringResource(id = R.string.trending_title),
            onClick = onTvClick
        )
        ListGridItemTV(
            title = stringResource(id = R.string.popular_title),
            listTVItem = uiState.value.listPopularTv
        )
    }
}