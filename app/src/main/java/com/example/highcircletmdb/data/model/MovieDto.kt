package com.example.highcircletmdb.data.model

import kotlinx.serialization.Serializable

@Serializable
data class MovieDto(
    val id: Int = -1,
    val title: String = "",
    val release_date: String = "",
    val poster_path: String? = null,
    val vote_average: Double = 0.0,
    val overview: String = "",
    val genre_ids: List<Int> = emptyList(),
    val runtime: Int? = null,
    val original_language: String = ""
)


@Serializable
data class PagedMovieResponse(
    val page: Int,
    val results: List<MovieDto>,
    val total_pages: Int,
    val total_results: Int
)