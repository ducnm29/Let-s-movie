package com.letsmovie.ui.movie

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.letsmovie.model.Movie
import com.letsmovie.model.Tv
import com.letsmovie.ui.tv.TvItem

@Composable
fun <T: Any>ListItemUI(
    modifier: Modifier = Modifier,
    listName: String,
    onClick: (itemId: String) -> Unit,
    listItem: List<T>
) {
    Column(
        modifier = Modifier
            .padding(top = 32.dp)

    ) {
        Text(
            text = listName,
            fontSize = 23.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = 16.dp)
        )
        LazyRow(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    top = 32.dp,
                )
        ){
               items(listItem) {
                   if (it is Movie) {
                       MovieItem(
                           movie = it,
                           onMovieClick = onClick
                       )
                       Spacer(modifier = Modifier.size(8.dp))
                   } else {
                       TvItem(
                           tv = it as Tv,
                           onTvClick = onClick
                       )
                       Spacer(modifier = Modifier.size(8.dp))
                   }
               }
        }
    }
}