package com.example.highcircletmdb.ui.screen.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.highcircletmdb.domain.model.mapper.toUIDetails
import com.example.highcircletmdb.domain.repository.IMovieRepository
import com.example.highcircletmdb.domain.repository.RepositoryResponse
import com.example.highcircletmdb.ui.model.MovieDetailsUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val repository: IMovieRepository,
) : ViewModel() {

    sealed class State {
        data object Loading : State()
        data object Error : State()
        data class Success(val data: MovieDetailsUi) : State()
    }

    private val _uiState = MutableStateFlow<State>(State.Loading)
    val uiState: StateFlow<State> = _uiState

    fun loadMovieDetails(movieId: Int) {
        viewModelScope.launch {
            _uiState.value = State.Loading
            when (val response = repository.getMovieDetails(movieId)) {
                is RepositoryResponse.Success -> {
                    val movie = response.data.toUIDetails()
                    _uiState.value = State.Success(movie)
                }

                is RepositoryResponse.Failure -> {
                    _uiState.value = State.Error
                }
            }
        }
    }
}