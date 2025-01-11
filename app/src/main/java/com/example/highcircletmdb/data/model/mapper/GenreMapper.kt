package com.example.highcircletmdb.data.model.mapper

import com.example.highcircletmdb.data.model.GenreListResponse
import com.example.highcircletmdb.domain.model.Genre

fun GenreListResponse.toDomainModel(): List<Genre> {
    return this.genres.map { Genre(id = it.id, name = it.name) }
}