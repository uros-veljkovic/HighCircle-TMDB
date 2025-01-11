package com.example.highcircletmdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.highcircletmdb.ui.Destination
import com.example.highcircletmdb.ui.screen.details.MovieDetailsScreen
import com.example.highcircletmdb.ui.screen.movie.MovieScreen
import com.example.highcircletmdb.ui.theme.HighCircleTMDBTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HighCircleTMDBTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()

    Scaffold { innerPadding ->
        NavHost(
            navController = navController, startDestination = Destination.AllMovies,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<Destination.AllMovies> {
                MovieScreen(onCardClick = {
                    navController.navigate(
                        Destination.MovieDetails(it)
                    )
                })
            }
            composable<Destination.MovieDetails> {
                MovieDetailsScreen(
                    movieId = it.toRoute<Destination.MovieDetails>().id,
                    navController = navController
                )
            }
        }
    }
}