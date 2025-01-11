package com.example.highcircletmdb.data.source.remote.impl

import com.example.highcircletmdb.data.model.GenreListResponse
import com.example.highcircletmdb.data.source.remote.IGenreRemoteDataSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

class TmdbGenreRemoteDataSource(private val client: HttpClient) : IGenreRemoteDataSource {

    override suspend fun getGenres(): GenreListResponse {
        return client.get("genre/movie/list").body()
    }
}