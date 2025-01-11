package com.example.highcircletmdb.domain.usecase

import com.example.highcircletmdb.domain.model.Movie
import com.example.highcircletmdb.domain.repository.IMovieRepository
import com.example.highcircletmdb.domain.repository.RepositoryResponse

class SearchMoviesUseCase(
    private val moviesRepository: IMovieRepository
) {

    sealed class Result {
        data class Success(val list: List<Movie>): Result()
        data object Failure: Result()
        data object WaitingForQuery: Result()
    }

    suspend fun invoke(query: String): Result {
        if (query.length < 3) return Result.WaitingForQuery

        return when(val result = moviesRepository.searchMovies(query)){
            is RepositoryResponse.Failure -> Result.Failure
            is RepositoryResponse.Success -> Result.Success(result.data)
        }
    }
}