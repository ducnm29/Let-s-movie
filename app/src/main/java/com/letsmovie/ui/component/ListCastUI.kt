package com.letsmovie.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.letsmovie.R
import com.letsmovie.model.DataCastResponse
import com.letsmovie.model.Result
import com.letsmovie.ui.cast.CastUI

@Composable
fun ListCastUI(
    modifier: Modifier = Modifier,
    dataResult: Result<DataCastResponse>
) {
    when (dataResult) {
        is Result.Loading -> {

        }

        is Result.Error -> {

        }

        is Result.Success -> {
            if (dataResult.data.listCast.any { it.character.isNotEmpty() }) {
                Text(
                    text = stringResource(id = R.string.cast_title),
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = modifier.padding(start = dimensionResource(id = R.dimen.spacer_horizontal2))
                )
                LazyRow(
                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacer_vertical2)),
                    contentPadding = PaddingValues(
                        horizontal = dimensionResource(id = R.dimen.spacer_horizontal2)
                    ),
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacer_vertical0))
                ) {
                    items(dataResult.data.listCast.filter { it.character.isNotEmpty() }) { castItem ->
                        CastUI(cast = castItem)
                    }
                }
            }
        }
    }
}