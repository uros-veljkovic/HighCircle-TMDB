package com.example.highcircletmdb.data.source.remote

import com.example.highcircletmdb.data.model.MovieDetailsDto
import com.example.highcircletmdb.data.model.MovieDto
import com.example.highcircletmdb.data.model.PagedMovieResponse
import io.ktor.client.statement.HttpResponse

interface IMovieRemoteDataSource {
    suspend fun getPopularMovies(): PagedMovieResponse
    suspend fun searchMovies(query: String): PagedMovieResponse
    suspend fun getMovieDetails(movieId: Int): MovieDetailsDto
}