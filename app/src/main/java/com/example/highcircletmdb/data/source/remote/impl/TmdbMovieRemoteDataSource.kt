package com.example.highcircletmdb.data.source.remote.impl

import com.example.highcircletmdb.data.model.MovieDetailsDto
import com.example.highcircletmdb.data.model.MovieDto
import com.example.highcircletmdb.data.model.PagedMovieResponse
import com.example.highcircletmdb.data.source.remote.IMovieRemoteDataSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class TmdbMovieRemoteDataSource(private val client: HttpClient) : IMovieRemoteDataSource {

    override suspend fun getPopularMovies(): PagedMovieResponse {
        return client.get("movie/popular").body()
    }

    override suspend fun searchMovies(query: String): PagedMovieResponse {
        return client.get("search/movie") {
            parameter("query", query)
        }.body()
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetailsDto {
        return client.get("movie/$movieId").body()
    }
}
