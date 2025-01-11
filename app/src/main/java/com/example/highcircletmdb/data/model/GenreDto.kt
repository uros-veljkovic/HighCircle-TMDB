package com.example.highcircletmdb.data.model

import kotlinx.serialization.Serializable

@Serializable
data class GenreDto(
    val id: Int,
    val name: String
)

@Serializable
data class GenreListResponse(
    val genres: List<GenreDto>
)