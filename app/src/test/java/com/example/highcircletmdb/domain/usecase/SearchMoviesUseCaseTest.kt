package com.example.highcircletmdb.domain.usecase

import com.example.highcircletmdb.domain.model.Movie
import com.example.highcircletmdb.domain.repository.IMovieRepository
import com.example.highcircletmdb.domain.repository.RepositoryResponse
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SearchMoviesUseCaseTest {

    private val movieRepository = mockk<IMovieRepository>(relaxed = true)
    private lateinit var sut: SearchMoviesUseCase

    @Before
    fun before() {
        sut = SearchMoviesUseCase(movieRepository)
    }

    @After
    fun after() {
        clearAllMocks()
    }

    @Test
    fun `test use case returns WaitingForQuery when query is shorter then 3 chars`() = runBlocking {
        val query = "Mo"

        val result = sut.invoke(query)

        assert(result is SearchMoviesUseCase.Result.WaitingForQuery)
    }

    @Test
    fun `test use case returns Failure when repository call fails`() = runBlocking {
        `given repository returns failure`()

        val result = sut.invoke("Movie")

        assert(result is SearchMoviesUseCase.Result.Failure)
    }

    @Test
    fun `test use case returns Success when repository call is successful`() = runBlocking {
        val movies = listOf(
            Movie(
                1, "Movie Title", "2022-01-01", "poster_path", 8.5,
                overview = "overview",
                genres = listOf(),
                runtime = "runtime",
                language = "language"
            )
        )
        `given repository returns success with movies`(movies)

        val result = sut.invoke("Movie")

        assert(result is SearchMoviesUseCase.Result.Success)
        assertEquals(movies, (result as SearchMoviesUseCase.Result.Success).list)
    }

    private fun `given repository returns failure`() {
        coEvery { movieRepository.searchMovies(any()) } returns RepositoryResponse.Failure(Throwable("Error fetching movies"))
    }

    private fun `given repository returns success with movies`(movies: List<Movie>) {
        coEvery { movieRepository.searchMovies(any()) } returns RepositoryResponse.Success(movies)
    }
}