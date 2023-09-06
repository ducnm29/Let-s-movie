package com.letsmovie.ui.component

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.unit.dp
import com.letsmovie.R
import com.letsmovie.model.DataCastResponse
import com.letsmovie.model.Result

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
            Text(
                text = stringResource(id = R.string.cast_title),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .padding(
                        top = dimensionResource(id = R.dimen.spacer_vertical1),
                        start = dimensionResource(id = R.dimen.spacer_vertical2)
                    )
                    .fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            LazyRow(
                modifier = modifier.padding(top = dimensionResource(id = R.dimen.spacer_vertical1)),
                contentPadding = PaddingValues(
                    horizontal = dimensionResource(id = R.dimen.spacer_vertical1)
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