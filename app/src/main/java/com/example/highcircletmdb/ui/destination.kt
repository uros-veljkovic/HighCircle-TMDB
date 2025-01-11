package com.example.highcircletmdb.ui

import kotlinx.serialization.Serializable

sealed class Destination {
    @Serializable
    data class MovieDetails(val id: Int) : Destination()

    @Serializable
    data object AllMovies : Destination()
}