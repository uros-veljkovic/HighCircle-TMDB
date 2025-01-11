package com.example.highcircletmdb.ui.screen.movie

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.highcircletmdb.ui.components.MoviesList
import com.example.highcircletmdb.ui.model.MovieUi
import com.example.highcircletmdb.ui.screen.empty.EmptyScreen
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(
    viewModel: MoviesViewModel = koinViewModel(),
    onSearchClicked: () -> Unit = {},
    onCardClick: (Int) -> Unit = {}
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Trending movies") },
                actions = {
                    IconButton(onClick = onSearchClicked) {
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = "Search"
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) { paddingValues ->
        when (state) {
            is MoviesViewModel.State.Loading -> {
                CircularProgressIndicator(modifier = Modifier.fillMaxSize())
            }

            is MoviesViewModel.State.Success -> {
                MoviesList(
                    modifier = Modifier.padding(paddingValues),
                    movies = (state as MoviesViewModel.State.Success).movies,
                    onCardClick = onCardClick
                )
            }

            is MoviesViewModel.State.Error -> {
                EmptyScreen(
                    title = "Information",
                    description = "Error occured. Check your internet connection"
                )
            }
        }
    }
}

val sampleMovies = listOf(
    MovieUi(1, "Inception", "2010-07-16", "https://image.tmdb.org/t/p/w500/poster.jpg", "8.8/10"),
    MovieUi(2, "The Matrix", "1999-03-31", null, "9.0/10"),
    MovieUi(3, "Interstellar", "2014-11-07", "https://image.tmdb.org/t/p/w500/poster.jpg", "8.6/10"),
    MovieUi(4, "The Dark Knight", "2008-07-18", "", "9.0/10")
)

@Preview
@Composable
private fun PreviewMoviesScreen() {
//    MovieScreen(sampleMovies)
}