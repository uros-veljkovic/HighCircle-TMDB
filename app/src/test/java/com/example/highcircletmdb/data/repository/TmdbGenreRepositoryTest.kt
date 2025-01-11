package com.example.highcircletmdb.data.repository

import com.example.highcircletmdb.data.model.GenreListResponse
import com.example.highcircletmdb.data.source.local.impl.TmdbGenreLocalDataSource
import com.example.highcircletmdb.data.source.remote.impl.TmdbGenreRemoteDataSource
import com.example.highcircletmdb.domain.model.Genre
import com.example.highcircletmdb.domain.repository.RepositoryResponse
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class TmdbGenreRepositoryTest {

    private val localDataSource = mockk<TmdbGenreLocalDataSource>(relaxed = true)
    private val remoteDataSource = mockk<TmdbGenreRemoteDataSource>(relaxed = true)
    private lateinit var sut: TmdbGenreRepository

    @Before
    fun before() {
        sut = TmdbGenreRepository(localDataSource, remoteDataSource)
    }

    @After
    fun after() {
        clearAllMocks()
    }

    @Test
    fun `test repository will return RepositoryResponse Failure when network call fails`() = runBlocking {
        `given fetching genres from network throws exception`()

        val response = sut.getGenres()

        assert(response is RepositoryResponse.Failure)
    }

    @Test
    fun `test repository will return RepositoryResponse Success when response is successful`() = runBlocking {
        `given fetching genres succeeds`()

        val response = sut.getGenres()

        `verify genres are cached`()
        assert(response is RepositoryResponse.Success)
    }

    @Test
    fun `test repository will return cached genres if genres are cached`() = runBlocking {
        `given genres are cached`()

        val response = sut.getGenres()
        assert(response is RepositoryResponse.Success)
    }

    private fun `given genres are cached`() {
        coEvery { localDataSource.getGenres() } returns listOf(Genre(id = 1, name = "Thriller"))
    }

    private fun `given fetching genres succeeds`() {
        coEvery { remoteDataSource.getGenres() } returns GenreListResponse(genres = listOf())
    }

    private fun `given fetching genres from network throws exception`() {
        coEvery { remoteDataSource.getGenres() } throws Exception()
    }

    private fun `verify genres are cached`() {
        coVerify { localDataSource.setGenres(any()) }
    }

    private fun `verify genre network call has not been triggered`() {
        coVerify(exactly = 0) { remoteDataSource.getGenres() }
    }

}