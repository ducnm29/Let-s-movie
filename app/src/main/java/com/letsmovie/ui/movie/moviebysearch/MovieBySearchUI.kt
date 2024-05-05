package com.letsmovie.ui.movie.moviebysearch

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.letsmovie.BuildConfig
import com.letsmovie.R
import com.letsmovie.model.Movie
import com.letsmovie.ui.component.SearchBarInDetailUI
import com.letsmovie.ui.movie.MovieItemHorizontalUI
import com.letsmovie.util.Define

@Composable
fun MovieBySearchUI(
    modifier: Modifier = Modifier,
    movieBySearchViewModel: MovieBySearchViewModel,
    onMovieDetailClick: (String) -> Unit,
    onBackClick: () -> Unit,
) {
    val movieData = movieBySearchViewModel.movieSearchStateFlow.collectAsLazyPagingItems()
    val focusRequester = remember {
        FocusRequester()
    }
    LaunchedEffect(true) {
        focusRequester.requestFocus()
    }
    Scaffold(

    ) { innerPadding ->
        MovieBySearchBodyUI(
            modifier = modifier.padding(
                top = innerPadding.calculateTopPadding()
            ),
            movieBySearchViewModel = movieBySearchViewModel,
            onMovieDetailClick = onMovieDetailClick,
            onBackClick = onBackClick,
            focusRequester = focusRequester,
            movieData = movieData,
            onSearchWithKeyWord = { keyword ->
                movieBySearchViewModel.setKeyWordSearch(keyword)
                movieBySearchViewModel.getMovieSearch(
                    language = Define.LANGUAGE_DEFAULT,
                    apiKey = BuildConfig.API_KEY,
                    includeAdult = false
                )
            }
        )
    }

}

@Composable
fun MovieBySearchBodyUI(
    modifier: Modifier = Modifier,
    movieBySearchViewModel: MovieBySearchViewModel,
    onMovieDetailClick: (String) -> Unit,
    onBackClick: () -> Unit,
    focusRequester: FocusRequester,
    movieData: LazyPagingItems<Movie>,
    onSearchWithKeyWord: (String) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        //Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_vertical3)))
        SearchBarInDetailUI(
            onValueChange = { keyword ->
                onSearchWithKeyWord(keyword)
            },
            searchKeywordValue = movieBySearchViewModel.keywordState.value,
            onBackClick = onBackClick,
            onCLearClick = {
                onSearchWithKeyWord("")
            },
            focusRequester = focusRequester
        )
        LazyColumn(
            contentPadding = PaddingValues(
                vertical = dimensionResource(id = R.dimen.spacer_vertical1)
            ),
            modifier = modifier
                .padding(
                    start = dimensionResource(id = R.dimen.spacer_horizontal2),
                    end = dimensionResource(id = R.dimen.spacer_horizontal2)
                )

        ) {
            items(movieData.itemCount) { index ->
                MovieItemHorizontalUI(
                    movie = movieData[index] ?: Define.MOVIE_SAMPLE,
                    onclick = onMovieDetailClick
                )
            }
        }
        if (movieData.itemCount == 0) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.search_suggest_title),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center
                )
            }

        }
    }
}