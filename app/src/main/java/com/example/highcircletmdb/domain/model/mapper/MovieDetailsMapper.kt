package com.example.highcircletmdb.domain.model.mapper

import com.example.highcircletmdb.data.model.GenreDto
import com.example.highcircletmdb.data.model.MovieDetailsDto
import com.example.highcircletmdb.domain.model.Genre
import com.example.highcircletmdb.domain.model.MovieDetails

fun MovieDetailsDto.toDomainModel(): MovieDetails {
    return MovieDetails(
        id = id,
        title = title,
        releaseDate = release_date,
        posterUrl = poster_path?.let { "https://image.tmdb.org/t/p/w500/$it" },
        rating = vote_average,
        overview = overview,
        genres = genres.map { it.toDomainModel() },
        runtime = runtime?.let { "${it / 60}h ${it % 60}m" } ?: "N/A",
        language = original_language.uppercase()
    )
}

fun GenreDto.toDomainModel(): Genre {
    return Genre(
        id = id,
        name = name
    )
}
