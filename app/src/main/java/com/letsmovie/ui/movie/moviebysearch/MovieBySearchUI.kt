package com.letsmovie.ui.movie.moviebysearch

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import com.letsmovie.R
import com.letsmovie.ui.component.SearchBarInDetailUI
import com.letsmovie.ui.movie.MovieItemHorizontalUI
import com.letsmovie.util.Define

@Composable
fun MovieBySearchUI(
    modifier: Modifier = Modifier,
    movieBySearchViewModel: MovieBySearchViewModel,
    onMovieDetailClick: (String) -> Unit,
    onBackClick: () -> Unit
) {
    val movieData = movieBySearchViewModel.movieSearchStateFlow.collectAsLazyPagingItems()
    LazyColumn(
        modifier = modifier
            .padding(
                top = dimensionResource(id = R.dimen.spacer_vertical3),
                start = dimensionResource(id = R.dimen.spacer_horizontal2),
                end = dimensionResource(id = R.dimen.spacer_horizontal2)
            )

    ) {
        item {
            SearchBarInDetailUI(
                onValueChange = { keyword ->
                    movieBySearchViewModel.keywordState.value = keyword
                    movieBySearchViewModel.getMovieSearch(
                        language = Define.LANGUAGE_DEFAULT,
                        apiKey = Define.API_KEY,
                        includeAdult = false
                    )
                },
                searchKeywordValue = movieBySearchViewModel.keywordState.value,
                onBackClick = onBackClick
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_horizontal3)))
        }
        items(movieData.itemCount) { index ->
            MovieItemHorizontalUI(
                movie = movieData[index] ?: Define.MOVIE_SAMPLE,
                onclick = onMovieDetailClick
            )
        }
        if (movieData.itemCount == 0) {
            item {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.search_suggest_title),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }

}