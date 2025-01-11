package com.example.highcircletmdb.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.highcircletmdb.ui.model.MovieUi
import com.example.highcircletmdb.ui.screen.movie.sampleMovies

@Composable
fun MoviesList(
    modifier: Modifier = Modifier,
    movies: List<MovieUi>,
    onCardClick: (Int) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(4.dp)
    ) {
        items(movies) { movie ->
            MovieCard(
                data = movie,
                onCardClick = onCardClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoviesListPreview() {
    MoviesList(movies = sampleMovies)
}