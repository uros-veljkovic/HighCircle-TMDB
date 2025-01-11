package com.example.highcircletmdb.data.repository

import com.example.highcircletmdb.data.model.MovieDetailsDto
import com.example.highcircletmdb.data.model.MovieDto
import com.example.highcircletmdb.data.model.PagedMovieResponse
import com.example.highcircletmdb.data.model.mapper.toDomainModel
import com.example.highcircletmdb.data.source.remote.IMovieRemoteDataSource
import com.example.highcircletmdb.data.util.getResponseSafely
import com.example.highcircletmdb.domain.model.Movie
import com.example.highcircletmdb.domain.model.MovieDetails
import com.example.highcircletmdb.domain.model.mapper.toDomainModel
import com.example.highcircletmdb.domain.repository.IGenreRepository
import com.example.highcircletmdb.domain.repository.IMovieRepository
import com.example.highcircletmdb.domain.repository.RepositoryResponse

class TmdbMovieRepository(
    private val remoteDataSource: IMovieRemoteDataSource,
    private val genreRepository: IGenreRepository
) : IMovieRepository {

    override suspend fun getPopularMovies(): RepositoryResponse<List<Movie>> {
        val moviesResponse = getResponseSafely<PagedMovieResponse> {
            remoteDataSource.getPopularMovies()
        }

        return when (moviesResponse) {
            is RepositoryResponse.Failure -> RepositoryResponse.Failure(moviesResponse.throwable)
            is RepositoryResponse.Success -> appendGenres(moviesResponse)
        }
    }

    override suspend fun searchMovies(query: String): RepositoryResponse<List<Movie>> {
        val moviesResponse = getResponseSafely<PagedMovieResponse> {
            remoteDataSource.searchMovies(query)
        }

        return when (moviesResponse) {
            is RepositoryResponse.Failure -> RepositoryResponse.Failure(moviesResponse.throwable)
            is RepositoryResponse.Success -> appendGenres(moviesResponse)
        }
    }

    private suspend fun appendGenres(
        moviesResponse: RepositoryResponse.Success<PagedMovieResponse>
    ): RepositoryResponse<List<Movie>> {
        return when (val genresResponse = genreRepository.getGenres()) {
            is RepositoryResponse.Failure -> RepositoryResponse.Failure(genresResponse.throwable)
            is RepositoryResponse.Success -> {
                val movies = moviesResponse.data.results.map { it.toDomainModel(genresResponse.data) }
                RepositoryResponse.Success(movies)
            }
        }
    }

    override suspend fun getMovieDetails(movieId: Int): RepositoryResponse<MovieDetails> {
        val movieDetailsResponse = getResponseSafely<MovieDetailsDto> {
            remoteDataSource.getMovieDetails(movieId)
        }

        return when (movieDetailsResponse) {
            is RepositoryResponse.Failure -> return RepositoryResponse.Failure(movieDetailsResponse.throwable)
            is RepositoryResponse.Success -> {
                when (val genresResponse = genreRepository.getGenres()) {
                    is RepositoryResponse.Failure -> RepositoryResponse.Failure(genresResponse.throwable)
                    is RepositoryResponse.Success -> {
                        RepositoryResponse.Success(movieDetailsResponse.data.toDomainModel())
                    }
                }
            }
        }
    }
}
