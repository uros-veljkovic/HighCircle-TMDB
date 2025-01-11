package com.example.highcircletmdb.data.source.local

import com.example.highcircletmdb.domain.model.Genre

interface IGenreLocalDataSource {
    suspend fun setGenres(genres: List<Genre>)
    suspend fun getGenres(): List<Genre>
}