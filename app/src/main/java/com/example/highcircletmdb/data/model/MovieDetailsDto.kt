package com.example.highcircletmdb.data.model

import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsDto(
    val id: Int = -1,
    val title: String = "",
    val release_date: String = "",
    val poster_path: String? = null,
    val vote_average: Double = 0.0,
    val overview: String = "",
    val genres: List<GenreDto> = emptyList(),
    val runtime: Int? = null,
    val original_language: String = ""
)