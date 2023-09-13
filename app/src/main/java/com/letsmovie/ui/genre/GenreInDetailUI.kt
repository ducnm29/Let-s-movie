package com.letsmovie.ui.genre

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.letsmovie.R
import com.letsmovie.model.Genre

@Composable
fun GenreInDetailUI(
    modifier: Modifier = Modifier,
    genre: Genre
) {
    Column(
        modifier = modifier
            .border(
                width = dimensionResource(id = R.dimen.border_line_size),
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(6.dp)
            )
    ) {
        Text(
            text = genre.name ?: "",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(
                horizontal = dimensionResource(id = R.dimen.spacer_horizontal2),
                vertical = dimensionResource(id = R.dimen.spacer_vertical1)
            )
        )

    }
}