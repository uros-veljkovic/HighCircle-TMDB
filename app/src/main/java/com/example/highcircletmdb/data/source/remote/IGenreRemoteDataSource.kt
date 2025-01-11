package com.example.highcircletmdb.data.source.remote

import com.example.highcircletmdb.data.model.GenreListResponse

interface IGenreRemoteDataSource {
    suspend fun getGenres(): GenreListResponse
}