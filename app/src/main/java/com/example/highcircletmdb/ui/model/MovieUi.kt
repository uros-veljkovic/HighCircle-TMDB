package com.example.highcircletmdb.ui.model

data class MovieUi(
    val id: Int,
    val title: String,
    val releaseDate: String,
    val posterUrl: String?,
    val rating: String
)

data class MovieDetailsUi(
    val title: String,
    val releaseDate: String,
    val fullPosterUrl: String?,
    val overview: String,
    val genres: String,
    val rating: String,
    val runtime: String,
    val language: String
)