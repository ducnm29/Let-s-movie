package com.letsmovie.ui.movie.moveingenre

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.letsmovie.R
import com.letsmovie.model.Movie
import com.letsmovie.ui.component.SearchBarUI
import com.letsmovie.ui.movie.MovieItem
import com.letsmovie.util.Define

@Composable
fun MovieInGenreUI(
    modifier: Modifier = Modifier,
    movieInGenreViewModel: MovieInGenreViewModel,
    onMovieClick: (String) -> Unit
) {
    val listState = rememberLazyGridState()
    val moviePaging = movieInGenreViewModel.movieInGenre.collectAsLazyPagingItems()

    BodyMovieInGenreUI(
        modifier = modifier,
        state = listState,
        onMovieClick = onMovieClick,
        movieList = moviePaging
    )
}

@Composable
fun BodyMovieInGenreUI(
    modifier: Modifier,
    onMovieClick: (String) -> Unit,
    movieList: LazyPagingItems<Movie>,
    state: LazyGridState = rememberLazyGridState()
) {
    Column(
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
            when (movieList.loadState.refresh) { //FIRST LOAD
                is LoadState.Error -> {

                }
                is LoadState.Loading -> { // Loading UI
                    item(
                        span = { GridItemSpan(5) }
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(8.dp),
                                text = "Refresh Loading"
                            )

                            CircularProgressIndicator(color = Color.Black)
                        }
                    }
                }
                else -> {}
            }

            when (movieList.loadState.append) {
                is LoadState.Error -> {
                    //TODO Pagination Error Item
                    //state.error to get error message
                }
                is LoadState.Loading -> { // Pagination Loading UI
                    item(
                        span = { GridItemSpan(5) }
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(text = "Loading movie")

                            CircularProgressIndicator(color = Color.Black)
                        }
                    }
                }
                else -> {}
            }
        }
    }
}