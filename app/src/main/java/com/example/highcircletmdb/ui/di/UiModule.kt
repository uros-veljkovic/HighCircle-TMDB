package com.example.highcircletmdb.ui.di

import com.example.highcircletmdb.ui.screen.details.MovieDetailsViewModel
import com.example.highcircletmdb.ui.screen.movie.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { MoviesViewModel(get()) }
    viewModel { MovieDetailsViewModel(get()) }
}