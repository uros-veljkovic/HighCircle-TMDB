package com.example.highcircletmdb.domain.model

data class MovieDetails(
    val id: Int,
    val title: String,
    val releaseDate: String,
    val posterUrl: String?,
    val rating: Double,
    val overview: String,
    val genres: List<Genre>,
    val runtime: String,
    val language: String
)