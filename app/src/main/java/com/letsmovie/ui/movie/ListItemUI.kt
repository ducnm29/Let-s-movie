package com.letsmovie.ui.movie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.letsmovie.R
import com.letsmovie.model.Movie
import com.letsmovie.model.Tv
import com.letsmovie.ui.tv.TvItem

@Composable
fun <T : Any> ListItemUI(
    modifier: Modifier = Modifier,
    listName: String,
    onClick: (itemId: String) -> Unit,
    listItem: List<T>
) {
    Column(
        modifier = modifier
            .padding(top = 32.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start

    ) {
        Text(
            text = listName,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(start = 16.dp)
        )
        LazyRow(
            modifier = Modifier.padding(top = 32.dp),
            contentPadding = PaddingValues(
                horizontal = dimensionResource(id = R.dimen.spacer_horizontal2)
            ),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacer_horizontal1))
        ) {
            items(listItem) {
                if (it is Movie) {
                    MovieItem(
                        movie = it,
                        onMovieClick = onClick
                    )
                } else {
                    TvItem(
                        tv = it as Tv,
                        onTvClick = onClick
                    )
                }
            }
        }
    }
}