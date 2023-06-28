package com.letsmovie.ui.movie

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.letsmovie.model.Movie
import com.letsmovie.model.MovieResponse
import com.letsmovie.model.Result

@Composable
fun ListMovieUI(
    modifier: Modifier = Modifier,
    listMovieName: String,
    onMovieClick: () -> Unit,
    listMovie: List<Movie>
) {
    Column(
        modifier = Modifier
            .padding(top = 32.dp)

    ) {
        Text(
            text = listMovieName,
            fontSize = 23.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = 16.dp)
        )
        LazyRow(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    top = 32.dp,
                )
        ){
            items(listMovie) {
                MovieItem(
                    movie = it,
                    onMovieClick = onMovieClick
                )
                Spacer(modifier = Modifier.size(8.dp))
            }
        }
    }
}
//= listOf(
//Movie("1","Fast and mad", "Duc nm", "https://picsum.photos/id/237/550/860",""),
//Movie("1","Fast and mad", "Duc nm", "https://picsum.photos/id/238/550/860",""),
//Movie("1","Fast and mad", "Duc nm", "https://picsum.photos/id/239/550/860",""),
//Movie("1","Fast and mad", "Duc nm", "https://picsum.photos/id/240/550/860",""),
//Movie("1","Fast and mad", "Duc nm", "https://picsum.photos/id/241/550/860",""),
//Movie("1","Fast and mad", "Duc nm", "https://picsum.photos/id/242/550/860",""),
//Movie("1","Fast and mad", "Duc nm", "https://picsum.photos/id/243/550/860",""),
//Movie("1","Fast and mad", "Duc nm", "https://picsum.photos/id/244/550/860",""),
//Movie("1","Fast and mad", "Duc nm", "https://picsum.photos/id/250/550/860",""),
//Movie("1","Fast and mad", "Duc nm", "https://picsum.photos/id/247/550/860",""),
//Movie("1","Fast and mad", "Duc nm", "https://picsum.photos/id/248/550/860",""),
//Movie("1","Fast and mad", "Duc nm", "https://picsum.photos/id/249/550/860",""),
//)