package com.example.highcircletmdb.data.model.mapper

import com.example.highcircletmdb.data.model.MovieDto
import com.example.highcircletmdb.domain.model.Genre
import com.example.highcircletmdb.domain.model.Movie

fun MovieDto.toDomainModel(genres: List<Genre>): Movie {
    return Movie(
        id = id,
        title = title,
        releaseDate = release_date,
        posterUrl = "https://image.tmdb.org/t/p/w500/$poster_path",
        rating = vote_average,
        overview = overview,
        genres = genres,
        runtime = runtime?.let { "${it / 60}h ${it % 60}m" } ?: "N/A",
        language = original_language.uppercase()
    )
}