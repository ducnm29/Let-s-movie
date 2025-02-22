package com.letsmovie.ui.tv

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
import com.letsmovie.ui.movie.MovieItem
import com.letsmovie.ui.tv.TvItem

@Composable
fun ListItemTV(
    modifier: Modifier = Modifier,
    listName: String,
    onClick: (itemId: String) -> Unit,
    listItem: List<Tv>
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start

    ) {
        Text(
            text = listName,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(start = 16.dp)
        )
        LazyRow(
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacer_vertical2)),
            contentPadding = PaddingValues(
                horizontal = dimensionResource(id = R.dimen.spacer_horizontal2)
            ),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacer_horizontal1))
        ) {
            items(listItem) {
                TvItem(
                    tv = it,
                    onTvClick = onClick
                )
            }
        }
    }
}