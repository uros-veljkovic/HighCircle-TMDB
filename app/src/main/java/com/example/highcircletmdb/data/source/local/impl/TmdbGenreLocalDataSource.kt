package com.example.highcircletmdb.data.source.local.impl

import com.example.highcircletmdb.data.source.local.IGenreLocalDataSource
import com.example.highcircletmdb.domain.model.Genre

class TmdbGenreLocalDataSource : IGenreLocalDataSource {

    private var cachedGenres: List<Genre>? = null

    override suspend fun setGenres(genres: List<Genre>) {
        this.cachedGenres = genres
    }

    override suspend fun getGenres(): List<Genre> {
        return cachedGenres ?: emptyList()
    }
}