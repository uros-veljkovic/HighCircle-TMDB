package com.example.highcircletmdb.domain.repository

import com.example.highcircletmdb.domain.model.Movie
import com.example.highcircletmdb.domain.model.MovieDetails

interface IMovieRepository {
    suspend fun getPopularMovies(): RepositoryResponse<List<Movie>>
    suspend fun searchMovies(query: String): RepositoryResponse<List<Movie>>
    suspend fun getMovieDetails(movieId: Int): RepositoryResponse<MovieDetails>
}