package com.letsmovie.ui.cast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.letsmovie.R
import com.letsmovie.ui.component.ListMovie
import com.letsmovie.ui.component.TextSectionUI
import com.letsmovie.util.Define

@Composable
fun CastDetailScreen(
    viewModel: CastDetailViewModel,
    onClickBack: () -> Unit,
    onMovieDetailClick: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 16.dp)
    ) {
        // Profile image section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(Define.BASE_IMG_URL_ORIGIN + uiState.backdropImage)
                    .scale(Scale.FILL)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.loading_image),
                error = painterResource(id = R.drawable.no_image_available)
            )
            Row(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
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
            AsyncImage(
                modifier = Modifier
                    .padding(
                        top = 210.dp,
                        start = 35.dp
                    )
                    .size(150.dp)
                    .clip(CircleShape),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(Define.BASE_IMG_URL_ORIGIN + uiState.credit.profilePath)
                    .scale(Scale.FIT)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.loading_image),
                error = painterResource(id = R.drawable.no_image_available)
            )
        }

        Text(
            modifier = Modifier.padding(
                top = dimensionResource(id = R.dimen.spacer_vertical2),
                start = dimensionResource(id = R.dimen.spacer_horizontal2),
                end = dimensionResource(id = R.dimen.spacer_horizontal2)
            ),
            text = uiState.credit.name,
            style = MaterialTheme.typography.headlineSmall
        )

        TextSectionUI(
            modifier = Modifier.padding(
                top = dimensionResource(id = R.dimen.spacer_vertical2),
                start = dimensionResource(id = R.dimen.spacer_horizontal2),
                end = dimensionResource(id = R.dimen.spacer_horizontal2)
            ),
            titleRes = R.string.biography,
            body = uiState.credit.biography
        )

        ListMovie(
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacer_vertical2)),
            listMovieName = Define.KNOWN_FOR,
            onClick = onMovieDetailClick,
            listMovie = uiState.listMovieRelated
        )
    }
}


@Preview
@Composable
fun PreviewCastDetail() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 16.dp)
    ) {
        // Profile image section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(Define.BASE_IMG_URL_ORIGIN + "")
                    .scale(Scale.FILL)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.loading_image),
                error = painterResource(id = R.drawable.no_image_available)
            )
            Row(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {},
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
            AsyncImage(
                modifier = Modifier
                    .padding(
                        top = 210.dp,
                        start = 35.dp
                    )
                    .size(150.dp)
                    .clip(CircleShape),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(Define.BASE_IMG_URL_ORIGIN + "")
                    .scale(Scale.FIT)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.loading_image),
                error = painterResource(id = R.drawable.no_image_available)
            )
        }

        Text(
            modifier = Modifier.padding(
                top = dimensionResource(id = R.dimen.spacer_vertical2),
                start = dimensionResource(id = R.dimen.spacer_horizontal2),
                end = dimensionResource(id = R.dimen.spacer_horizontal2)
            ),
            text = "Name",
            style = MaterialTheme.typography.headlineSmall
        )

        TextSectionUI(
            modifier = Modifier.padding(
                top = dimensionResource(id = R.dimen.spacer_vertical2),
                start = dimensionResource(id = R.dimen.spacer_horizontal2),
                end = dimensionResource(id = R.dimen.spacer_horizontal2)
            ),
            titleRes = R.string.biography,
            body = "Biography"
        )

        ListMovie(
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacer_vertical2)),
            listMovieName = Define.KNOWN_FOR,
            onClick = {},
            listMovie = listOf()
        )
    }
}
