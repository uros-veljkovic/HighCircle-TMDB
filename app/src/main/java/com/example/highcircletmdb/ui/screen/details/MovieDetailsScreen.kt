package com.example.highcircletmdb.ui.screen.details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.highcircletmdb.ui.screen.empty.EmptyScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieDetailsScreen(
    movieId: Int,
    viewModel: MovieDetailsViewModel = koinViewModel()
) {
    LaunchedEffect(movieId) {
        viewModel.loadMovieDetails(movieId)
    }

    val uiState = viewModel.uiState.collectAsState()

    when (val state = uiState.value) {
        is MovieDetailsViewModel.State.Loading -> {
            CircularProgressIndicator(modifier = Modifier.fillMaxSize())
        }

        is MovieDetailsViewModel.State.Success -> {
            MovieDetailsScreenContent(movie = state.data)
        }

        is MovieDetailsViewModel.State.Error -> {
            EmptyScreen(title = "Information", description = "Failed to fetch details")
        }
    }
}


