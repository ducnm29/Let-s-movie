package com.letsmovie.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.letsmovie.R

@Composable
fun TextSectionUI(
    modifier: Modifier = Modifier,
    titleRes: Int,
    body: String,
) {
    Text(
        text = stringResource(id = titleRes),
        style = MaterialTheme.typography.headlineSmall,
        modifier = modifier.fillMaxWidth(),
        textAlign = TextAlign.Start
    )
    Text(
        text = body,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier
            .padding(
                start = dimensionResource(id = R.dimen.spacer_horizontal2),
                end = dimensionResource(id = R.dimen.spacer_horizontal2),
                top = dimensionResource(id = R.dimen.spacer_vertical1)
            )
    )
}