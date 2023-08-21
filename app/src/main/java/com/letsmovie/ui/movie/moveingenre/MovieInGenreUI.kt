package com.letsmovie.ui.movie.moveingenre

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.letsmovie.R
import com.letsmovie.model.Movie
import com.letsmovie.ui.component.ErrorAndRetryItem
import com.letsmovie.ui.component.SearchBarUI
import com.letsmovie.ui.movie.MovieItem
import com.letsmovie.util.Define
import kotlinx.coroutines.launch

@Composable
fun MovieInGenreUI(
    modifier: Modifier = Modifier,
    movieInGenreViewModel: MovieInGenreViewModel,
    onMovieClick: (String) -> Unit
) {
    val listState = rememberLazyGridState()
    val moviePaging = movieInGenreViewModel.movieInGenre.collectAsLazyPagingItems()
    val scope = rememberCoroutineScope()

    BodyMovieInGenreUI(
        modifier = modifier,
        state = listState,
        onMovieClick = onMovieClick,
        movieList = moviePaging,
        onFABClick = {
            scope.launch {
                listState.animateScrollToItem(0, 0)
            }
        }
    )
}

@Composable
fun BodyMovieInGenreUI(
    modifier: Modifier,
    onMovieClick: (String) -> Unit,
    movieList: LazyPagingItems<Movie>,
    state: LazyGridState = rememberLazyGridState(),
    onFABClick: () -> Unit
) {
    Box(
        modifier = modifier.padding(top = dimensionResource(id = R.dimen.spacer_vertical2))
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(120.dp),
            state = state,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacer_vertical2)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacer_horizontal3)),
            contentPadding = PaddingValues(
                horizontal = dimensionResource(id = R.dimen.spacer_horizontal2),
                vertical = dimensionResource(id = R.dimen.spacer_vertical1)
            )

        ) {
            //Search Bar section
            item(span = { GridItemSpan(5) }) {
                SearchBarUI()
            }
            //List movie section
            items(
                movieList.itemCount
            ) { index ->
                MovieItem(
                    movie = movieList[index] ?: Define.MOVIE_SAMPLE,
                    onMovieClick = onMovieClick
                )
            }

            // Movies data is loaded at the first time
            when (movieList.loadState.refresh) {
                is LoadState.Error -> {
                    item(
                        span = { GridItemSpan(5) }
                    ) {
                        ErrorAndRetryItem(
                            errRes = R.string.common_error,
                            retryRes = R.string.common_retry,
                            pagingData = movieList
                        )
                    }
                }

                is LoadState.Loading -> { // Loading UI
                    item(
                        span = { GridItemSpan(5) }
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                modifier = Modifier.padding(8.dp),
                                text = stringResource(id = R.string.loading_movie)
                            )
                            CircularProgressIndicator(color = MaterialTheme.colorScheme.tertiary)
                        }
                    }
                }
                else -> {}
            }

            // Movies data is loaded and inserted into end of list
            when (movieList.loadState.append) {
                is LoadState.Error -> {
                    item(
                        span = { GridItemSpan(5) }
                    ) {
                        ErrorAndRetryItem(
                            errRes = R.string.common_error,
                            retryRes = R.string.common_retry,
                            pagingData = movieList
                        )
                    }
                }

                is LoadState.Loading -> { // Pagination Loading UI
                    item(
                        span = { GridItemSpan(5) }
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(text = stringResource(id = R.string.loading_movie))

                            CircularProgressIndicator(color = MaterialTheme.colorScheme.tertiary)
                        }
                    }
                }

                else -> {}
            }
        }
        FloatingActionButton(
            modifier = Modifier
                .align(
                    Alignment.BottomEnd
                )
                .padding(dimensionResource(id = R.dimen.spacer_vertical1)),
            onClick = onFABClick,
        ) {
            Icon(Icons.Filled.ArrowUpward, "Animate to start of page")
        }
    }
}