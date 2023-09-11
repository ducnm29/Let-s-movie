package com.letsmovie.ui.component

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.letsmovie.R
import com.letsmovie.model.Genre
import com.letsmovie.ui.genre.GenreInDetailUI

@Composable
fun GenreListInDetailUI(
    modifier: Modifier = Modifier,
    listGenre: List<Genre>
) {
    if (listGenre.isNotEmpty()) {
        Column(
            modifier = modifier
        ) {
            Text(
                text = stringResource(id = R.string.genre_title),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(
                    top = dimensionResource(id = R.dimen.spacer_vertical1),
                    start = dimensionResource(id = R.dimen.spacer_horizontal2)
                ).fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            LazyRow(
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacer_vertical1)),
                contentPadding = PaddingValues(horizontal = dimensionResource(id = R.dimen.spacer_horizontal2)),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacer_horizontal2))
            ) {
                items(listGenre) { genre ->
                    GenreInDetailUI(genre = genre)
                }

            }
        }
    }
}