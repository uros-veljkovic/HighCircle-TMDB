package com.example.highcircletmdb.domain.repository

import com.example.highcircletmdb.domain.model.Genre

interface IGenreRepository {
    suspend fun getGenres(): RepositoryResponse<List<Genre>>
}