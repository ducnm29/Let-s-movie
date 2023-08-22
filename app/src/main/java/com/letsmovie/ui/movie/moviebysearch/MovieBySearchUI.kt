package com.letsmovie.ui.movie.moviebysearch

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.letsmovie.ui.component.SearchBarUI

@Composable
fun MovieBySearchUI(
    modifier: Modifier = Modifier
) {
    LazyColumn(

    ){
        item {
            SearchBarUI(onClick = {})
        }
    }

}