package com.example.highcircletmdb.ui.screen.movie

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.highcircletmdb.domain.model.mapper.toUIModel
import com.example.highcircletmdb.domain.repository.IMovieRepository
import com.example.highcircletmdb.domain.repository.RepositoryResponse
import com.example.highcircletmdb.ui.model.MovieUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val repository: IMovieRepository // Inject your repository here
) : ViewModel() {

    sealed class State {
        data class Success(val movies: List<MovieUi>) : State()
        data class Error(val message: String) : State()
        data object Loading : State()
    }

    private val _state = MutableStateFlow<State>(State.Loading)
    val state = _state.asStateFlow()

    private var allMovies: List<MovieUi> = emptyList()

    init {
        loadMovies()
    }

    fun loadMovies() {
        viewModelScope.launch {
            _state.value = State.Loading
            when (val result = repository.getPopularMovies()) {
                is RepositoryResponse.Success -> {
                    Log.d("Movie", result.data.first().toString())
                    allMovies = result.data.map { it.toUIModel() }
                    _state.value = State.Success(allMovies)
                }

                is RepositoryResponse.Failure -> {
                    Log.d("Error", result.throwable.toString())
                    _state.value = State.Error("Failed to load movies.")
                }
            }
        }
    }

    fun searchMovies(query: String) {
        if (query.isBlank()) {
            _state.value = State.Success(allMovies) // Show all movies if the query is blank
            return
        }

        val filteredMovies = allMovies.filter { it.title.contains(query, ignoreCase = true) }
        _state.value = if (filteredMovies.isEmpty()) {
            State.Error("No movies found for \"$query\".")
        } else {
            State.Success(filteredMovies)
        }
    }
}