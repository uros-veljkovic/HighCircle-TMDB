package com.example.highcircletmdb.ui.screen.details

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.highcircletmdb.ui.screen.empty.EmptyScreen
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsScreen(
    movieId: Int,
    navController: NavController,
    viewModel: MovieDetailsViewModel = koinViewModel()
) {
    LaunchedEffect(movieId) {
        viewModel.loadMovieDetails(movieId)
    }

    val uiState = viewModel.uiState.collectAsState()

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text(text = "Details") },
            navigationIcon = {
                Icon(
                    modifier = Modifier.clickable {
                        navController.navigateUp()
                    },
                    imageVector = Icons.Default.Close, contentDescription = null
                )
            },
            modifier = Modifier.fillMaxWidth()
        )
    }) { paddingValues ->
        when (val state = uiState.value) {
            is MovieDetailsViewModel.State.Loading -> {
                Column(
                    modifier = Modifier.padding(paddingValues),
                    verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(modifier = Modifier)
                }
            }

            is MovieDetailsViewModel.State.Success -> {
                MovieDetailsScreenContent(movie = state.data)
            }

            is MovieDetailsViewModel.State.Error -> {
                EmptyScreen(title = "Information", description = "Failed to fetch details")
            }
        }
    }
}


