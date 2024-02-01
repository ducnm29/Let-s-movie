package com.letsmovie.ui.movie.moviedetail


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.letsmovie.R
import com.letsmovie.model.Credit
import com.letsmovie.model.Movie
import com.letsmovie.model.Result
import com.letsmovie.model.TagIcon
import com.letsmovie.ui.component.FooterMovieDetailUI
import com.letsmovie.ui.component.GenreListInDetailUI
import com.letsmovie.ui.component.ListCastUI
import com.letsmovie.ui.component.ListItemWithData
import com.letsmovie.ui.component.ListMovie
import com.letsmovie.ui.component.ProgressLoading
import com.letsmovie.ui.component.TagIconUI
import com.letsmovie.ui.component.TextSectionUI
import com.letsmovie.ui.theme.background_card_item
import com.letsmovie.util.Define
import com.letsmovie.util.Util.openLinkInBrowser

@Composable
fun MovieDetailUI(
    modifier: Modifier = Modifier,
    movieDetailViewModel: MovieDetailViewModel,
    onClickBack: () -> Unit,
    onMovieClickDetail: (String) -> Unit,
    onGenreClick: (String, String) -> Unit
) {
    val uiState by movieDetailViewModel.uiState.collectAsStateWithLifecycle()
    val currentContext = LocalContext.current

    Box {
        MovieDetailBodyUI(
            modifier = modifier,
            movie = uiState.movie,
            listCast = uiState.listCast,
            listRecommendationsMovie = uiState.listRecommendationMovie,
            onClickBack = onClickBack,
            onMovieClickDetail = onMovieClickDetail,
            onClickOpenLink = { link ->
                currentContext.openLinkInBrowser(link = link)
            },
            onGenreClick = onGenreClick
        )
        if (uiState.isLoading) {
            ProgressLoading(
                modifier = Modifier
                    .zIndex(1f)
                    .fillMaxSize()
                    .background(background_card_item)
                    .alpha(0.5f)
            )
        }
        if (uiState.isError) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = stringResource(id = R.string.common_error))
            }
        }
    }
}

@Composable
fun MovieDetailBodyUI(
    modifier: Modifier = Modifier,
    movie: Movie,
    listCast: List<Credit>,
    listRecommendationsMovie: List<Movie>,
    onClickBack: () -> Unit,
    onMovieClickDetail: (String) -> Unit,
    onClickOpenLink: (String) -> Unit,
    onGenreClick: (String, String) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp),
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(Define.BASE_IMG_URL_ORIGIN + movie.imgBackground)
                    .scale(Scale.FILL)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.loading_image),
                alpha = 0.4f,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                error = painterResource(id = R.drawable.no_image_available)
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    IconButton(
                        onClick = onClickBack,
                        modifier = Modifier.padding(
                            top = dimensionResource(id = R.dimen.spacer_vertical2),
                            start = dimensionResource(id = R.dimen.spacer_horizontal2)
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.KeyboardArrowLeft,
                            contentDescription = null,
                        )
                    }
                    Text(
                        text = movie.movieName,
                        fontWeight = FontWeight.Medium,
                        fontSize = 22.sp,
                        modifier = Modifier
                            .weight(1f)
                            .padding(top = dimensionResource(id = R.dimen.spacer_vertical3)),
                        textAlign = TextAlign.Center,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    IconButton(
                        onClick = {

                        },
                        modifier = Modifier.padding(
                            top = dimensionResource(id = R.dimen.spacer_vertical2),
                            end = dimensionResource(id = R.dimen.spacer_horizontal2)
                        )
                    ) {
                        Icon(
                            imageVector = Icons.TwoTone.Favorite,
                            contentDescription = null,
                        )
                    }
                }
                Card(
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .width(220.dp)
                        .height(360.dp)
                        .padding(top = 12.dp)
                ) {
                    AsyncImage(
                        model = ImageRequest
                            .Builder(LocalContext.current)
                            .data(Define.BASE_IMG_URL_ORIGIN + movie.imgPoster)
                            .scale(Scale.FILL)
                            .build(),
                        contentDescription = null,
                        placeholder = painterResource(id = R.drawable.loading_image),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize(),
                        error = painterResource(id = R.drawable.no_image_available)
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    TagIconUI(
                        tagIcon = TagIcon(
                            tagName = movie.releaseDate,
                            tagIconImageVector = Icons.Default.DateRange
                        )
                    )
                    TagIconUI(
                        tagIcon = TagIcon(
                            tagName = movie.voteAverage.toString(),
                            tagIconImageVector = Icons.Default.StarRate
                        )
                    )
                    TagIconUI(
                        tagIcon = TagIcon(
                            tagName = movie.runtime.toString(),
                            tagIconImageVector = Icons.Default.AccessTime
                        )
                    )
                }
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            TextSectionUI(
                modifier = Modifier.padding(
                    top = dimensionResource(id = R.dimen.spacer_vertical2),
                    start = dimensionResource(id = R.dimen.spacer_horizontal2),
                    end = dimensionResource(id = R.dimen.spacer_horizontal2)
                ),
                titleRes = R.string.story_line_title,
                body = movie.movieOverview
            )

            GenreListInDetailUI(
                listGenre = movie.genreList,
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacer_vertical1)),
                onGenreClick = onGenreClick
            )

            ListCastUI(
                listCredit = listCast,
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacer_vertical2))
            )

            ListMovie(
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacer_vertical2)),
                listMovieName = stringResource(id = R.string.related_movie_title),
                onClick = onMovieClickDetail,
                listMovie = listRecommendationsMovie)

            FooterMovieDetailUI(
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacer_vertical3)),
                movie = movie,
                onCLickOpenLink = onClickOpenLink
            )

            Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.spacer_vertical2)))
        }
    }
}