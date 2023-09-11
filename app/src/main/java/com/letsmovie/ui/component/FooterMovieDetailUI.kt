package com.letsmovie.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.letsmovie.R
import com.letsmovie.model.Movie

@Composable
fun FooterMovieDetailUI(
    modifier: Modifier = Modifier,
    movie: Movie
) {
    Column(
        modifier = modifier.padding(
            start = dimensionResource(id = R.dimen.spacer_vertical2),
            end = dimensionResource(id = R.dimen.spacer_vertical2)
        )
    ) {

    }

}