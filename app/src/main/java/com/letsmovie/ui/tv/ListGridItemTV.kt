package com.letsmovie.ui.tv

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.letsmovie.model.Tv

@Composable
fun ListGridItemTV(
    modifier: Modifier = Modifier,
    state: LazyGridState = rememberLazyGridState(),
    title: String,
    listTVItem: List<Tv>
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(all = 16.dp),
            text = title,
            fontWeight = FontWeight.Medium,
            fontSize = 23.sp,
        )
        LazyVerticalGrid(
            modifier = modifier,
            columns = GridCells.Adaptive(150.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
            state = state
        ) {
            items(listTVItem.size) {
                TvGridUI(tv = listTVItem[it])
            }
        }
    }
}