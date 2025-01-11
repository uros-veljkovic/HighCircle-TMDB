package com.example.highcircletmdb.domain.di

import com.example.highcircletmdb.domain.usecase.SearchMoviesUseCase
import org.koin.dsl.module

val domainModule = module {
    single { SearchMoviesUseCase(get()) }

}