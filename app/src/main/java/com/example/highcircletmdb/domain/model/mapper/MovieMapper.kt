package com.example.highcircletmdb.domain.model.mapper

import com.example.highcircletmdb.domain.model.Movie
import com.example.highcircletmdb.domain.model.MovieDetails
import com.example.highcircletmdb.ui.model.MovieDetailsUi
import com.example.highcircletmdb.ui.model.MovieUi

fun Movie.toUIModel(): MovieUi {
    return MovieUi(
        id = id,
        title = title,
        releaseDate = releaseDate,
        posterUrl = posterUrl,
        rating = "$rating/10"
    )
}

fun MovieDetails.toUIDetails(): MovieDetailsUi {
    return MovieDetailsUi(
        title = title,
        releaseDate = releaseDate,
        fullPosterUrl = posterUrl,
        overview = overview,
        genres = genres.joinToString { it.name },
        rating = "$rating/10",
        runtime = runtime,
        language = language
    )
}