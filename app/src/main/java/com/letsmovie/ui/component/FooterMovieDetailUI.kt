package com.letsmovie.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.letsmovie.R
import com.letsmovie.model.Movie
import com.letsmovie.util.Util

@Composable
fun FooterMovieDetailUI(
    modifier: Modifier = Modifier,
    movie: Movie,
    onCLickOpenLink: (String) -> Unit
) {
    Divider(
        color = MaterialTheme.colorScheme.outline,
        thickness = dimensionResource(id = R.dimen.border_line_size),
        modifier = modifier.alpha(0.5f)
    )
    Column(
        modifier = modifier.padding(
            start = dimensionResource(id = R.dimen.spacer_vertical2),
            end = dimensionResource(id = R.dimen.spacer_vertical2)
        )
    ) {
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_vertical1)))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextElementUI(
                modifier = Modifier.width(dimensionResource(id = R.dimen.movie_item_height)),
                titleRes = R.string.language_title,
                body = movie.originalLanguage ?: Util.getDeviceLanguage()
            )
            TextElementUI(
                modifier = Modifier.width(dimensionResource(id = R.dimen.movie_item_height)),
                titleRes = R.string.status,
                body = movie.status ?: "Released"
            )
        }
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_vertical1)))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextElementUI(
                modifier = Modifier.width(dimensionResource(id = R.dimen.movie_item_height)),
                titleRes = R.string.production_company,
                body = if (movie.productionCompanies.isNotEmpty()) {
                    movie.productionCompanies[0].name ?: ""
                } else {
                    "..."
                }
            )
            ClickAbleTextElementUI(
                modifier = Modifier.width(dimensionResource(id = R.dimen.movie_item_height)),
                titleRes = R.string.homepage_title,
                body = movie.homePage ?: "",
                onCLickOpenLink = onCLickOpenLink
            )
        }
    }
}

@Composable
fun TextElementUI(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    body: String
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = titleRes),
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_vertical0)))
        Text(
            text = body,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 2
        )
    }
}

@Composable
fun ClickAbleTextElementUI(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    body: String,
    onCLickOpenLink: (String) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = titleRes),
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_vertical0)))
        Text(
            text = body,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 2,
            modifier = Modifier.clickable {
                onCLickOpenLink(body)
            }
        )
    }
}