package com.example.highcircletmdb.data.repository

import com.example.highcircletmdb.data.model.GenreListResponse
import com.example.highcircletmdb.data.model.mapper.toDomainModel
import com.example.highcircletmdb.data.source.local.IGenreLocalDataSource
import com.example.highcircletmdb.data.source.remote.IGenreRemoteDataSource
import com.example.highcircletmdb.data.util.getResponseSafely
import com.example.highcircletmdb.domain.model.Genre
import com.example.highcircletmdb.domain.repository.IGenreRepository
import com.example.highcircletmdb.domain.repository.RepositoryResponse

class TmdbGenreRepository(
    private val localDataSource: IGenreLocalDataSource,
    private val remoteDataSource: IGenreRemoteDataSource
) : IGenreRepository {
    override suspend fun getGenres(): RepositoryResponse<List<Genre>> {
        if (localDataSource.getGenres().isNotEmpty()) {
            return RepositoryResponse.Success(localDataSource.getGenres())
        } else {
            val genresResponse = getResponseSafely<GenreListResponse> {
                remoteDataSource.getGenres()
            }
            return when (genresResponse) {
                is RepositoryResponse.Failure -> RepositoryResponse.Failure(genresResponse.throwable)
                is RepositoryResponse.Success -> {
                    val genres = genresResponse.data.toDomainModel()
                    localDataSource.setGenres(genres)
                    RepositoryResponse.Success(genres)
                }
            }
        }
    }
}