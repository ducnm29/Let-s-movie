package com.letsmovie.ui.movie.moviebytype

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material3.Scaffold
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
import com.letsmovie.ui.component.SearchBarInDetailUI
import com.letsmovie.ui.movie.MovieItem
import com.letsmovie.ui.movie.movebygenre.BodyMovieByGenreUI
import com.letsmovie.util.Define
import kotlinx.coroutines.launch

@Composable
fun MovieByTypeUI(
    modifier: Modifier = Modifier,
    typeName: String,
    movieByTypeViewModel: MovieByTypeViewModel,
    onMovieClick: (String) -> Unit,
    onBackClick: () -> Unit
) {
    val listState = rememberLazyGridState()
    val moviePaging = movieByTypeViewModel.movieByTypeStateFlow.collectAsLazyPagingItems()
    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    scope.launch {
                        listState.animateScrollToItem(0, 0)
                    }
                },
            ) {
                // Button scroll to header
                AnimatedVisibility(visible = listState.canScrollBackward) {
                    Icon(Icons.Filled.ArrowUpward, "Animate to start of page")
                }
                AnimatedVisibility(visible = !listState.canScrollBackward) {
                    Text(
                        text = typeName,
                        modifier = Modifier.padding(
                            start = dimensionResource(id = R.dimen.spacer_horizontal2),
                            end = dimensionResource(id = R.dimen.spacer_horizontal2)
                        )
                    )
                }

            }
        }
    ) { innerPadding ->
        BodyMovieByTypeUI(
            modifier = modifier.padding(
                top = innerPadding.calculateTopPadding()
            ),
            state = listState,
            onMovieClick = onMovieClick,
            movieList = moviePaging,
            onBackClick = onBackClick
        )
    }
}

@Composable
fun BodyMovieByTypeUI(
    modifier: Modifier,
    onMovieClick: (String) -> Unit,
    movieList: LazyPagingItems<Movie>,
    state: LazyGridState = rememberLazyGridState(),
    onBackClick: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        SearchBarInDetailUI(
            onBackClick = onBackClick,
            searchKeywordValue = "",
            onValueChange = {},
            onCLearClick = {},
        )
        LazyVerticalGrid(
            columns = GridCells.Adaptive(120.dp),
            state = state,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacer_vertical2)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacer_horizontal4)),
            contentPadding = PaddingValues(
                horizontal = dimensionResource(id = R.dimen.spacer_horizontal2),
                vertical = dimensionResource(id = R.dimen.spacer_vertical1)
            )

        ) {
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
                        span = { GridItemSpan(maxLineSpan) }
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
                        span = { GridItemSpan(maxLineSpan) }
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
                        span = { GridItemSpan(maxLineSpan) }
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
                        span = { GridItemSpan(maxLineSpan) }
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
    }
}