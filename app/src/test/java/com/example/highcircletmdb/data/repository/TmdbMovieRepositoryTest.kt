package com.example.highcircletmdb.data.repository

import com.example.highcircletmdb.data.model.PagedMovieResponse
import com.example.highcircletmdb.data.source.remote.impl.TmdbMovieRemoteDataSource
import com.example.highcircletmdb.domain.model.Genre
import com.example.highcircletmdb.domain.repository.IGenreRepository
import com.example.highcircletmdb.domain.repository.RepositoryResponse
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test


class TmdbMovieRepositoryTest {

    private lateinit var genreRepository: IGenreRepository
    private lateinit var remoteDataSource: TmdbMovieRemoteDataSource

    private lateinit var sut: TmdbMovieRepository

    @Before
    fun before() {
        genreRepository = mockk(relaxed = true)
        remoteDataSource = mockk(relaxed = true)
        sut = TmdbMovieRepository(remoteDataSource, genreRepository)
    }

    @After
    fun after() {
        clearAllMocks()
    }

    @Test
    fun `test getPopularMovies is successful when movies and genres network call is successful`() =
        runBlocking {
            `given movies are successfully fetched`()
            `given genres are successfully fetched`()

            val result = sut.getPopularMovies()

            assert(result is RepositoryResponse.Success)
        }

    @Test
    fun `test getPopularMovies fails when fetching genres fails`() =
        runBlocking {
            `given movies are successfully fetched`()
            `given genres fetching fails`()

            val result = sut.getPopularMovies()

            assert(result is RepositoryResponse.Failure)
        }


    @Test
    fun `test getPopularMovies fails when fetching movies fails`() = runBlocking {
        `given movies fetching fails`()

        val result = sut.getPopularMovies()

        assert(result is RepositoryResponse.Failure)
    }

    @Test
    fun `test searchMovies is successful when movies and genres are fetched`() = runBlocking {
        `given movies are successfully fetched`()
        `given genres are successfully fetched`()

        val result = sut.searchMovies("Spiderman")

        assert(result is RepositoryResponse.Success)
    }

    private fun `given search result is successfully fetched`() {
        coEvery { remoteDataSource.searchMovies(any()) } returns PagedMovieResponse(
            page = 1,
            results = listOf(),
            total_pages = 7284,
            total_results = 50
        )
    }

    private fun `given movies are successfully fetched`() {
        coEvery { remoteDataSource.getPopularMovies() } returns PagedMovieResponse(
            page = 1,
            results = listOf(),
            total_pages = 7284,
            total_results = 50
        )
    }

    private fun `given genres are successfully fetched`() {
        coEvery { genreRepository.getGenres() } returns RepositoryResponse.Success(
            listOf(Genre(id = 1, name = "Thriller"))
        )
    }

    private fun `given movies fetching fails`() {
        coEvery { remoteDataSource.getPopularMovies() } throws Exception()
    }

    private fun `given genres fetching fails`() {
        coEvery { genreRepository.getGenres() } returns RepositoryResponse.Failure(Exception())
    }
}