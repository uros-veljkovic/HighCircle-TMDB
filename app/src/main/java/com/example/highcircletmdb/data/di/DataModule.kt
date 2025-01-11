package com.example.highcircletmdb.data.di

import com.example.highcircletmdb.data.KtorClientProvider
import com.example.highcircletmdb.data.repository.TmdbGenreRepository
import com.example.highcircletmdb.data.repository.TmdbMovieRepository
import com.example.highcircletmdb.data.source.local.IGenreLocalDataSource
import com.example.highcircletmdb.data.source.local.impl.TmdbGenreLocalDataSource
import com.example.highcircletmdb.data.source.remote.IGenreRemoteDataSource
import com.example.highcircletmdb.data.source.remote.IMovieRemoteDataSource
import com.example.highcircletmdb.data.source.remote.impl.TmdbGenreRemoteDataSource
import com.example.highcircletmdb.data.source.remote.impl.TmdbMovieRemoteDataSource
import com.example.highcircletmdb.domain.repository.IGenreRepository
import com.example.highcircletmdb.domain.repository.IMovieRepository
import io.ktor.client.HttpClient
import org.koin.dsl.module

val dataModule = module {

    // Client
    single<HttpClient> { KtorClientProvider.client }

    // Repository
    single<IGenreRepository> { TmdbGenreRepository(get(), get()) }
    single<IMovieRepository> { TmdbMovieRepository(get(), get()) }

    // Remote data sources
    factory<IMovieRemoteDataSource> { TmdbMovieRemoteDataSource(get()) }
    factory<IGenreRemoteDataSource> { TmdbGenreRemoteDataSource(get()) }

    // Local data sources
    factory<IGenreLocalDataSource> { TmdbGenreLocalDataSource() }
}