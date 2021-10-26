package com.example.movieapp.presentation.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.common.Constants
import com.example.movieapp.common.Resource
import com.example.movieapp.domain.use_cases.getMovie.GetMovieUseCase
import com.example.movieapp.domain.use_cases.getMovies.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase,
    savedStateHandle: SavedStateHandle
):ViewModel() {

    private val _state= mutableStateOf(MoviesDetailsViewState())
    val state: State<MoviesDetailsViewState> =_state

    init {
        savedStateHandle.get<String>(Constants.PARAM_MOVIE_ID)?.let {
            getMovie(it)

        }
    }

    private fun getMovie(movieId:String){
        getMovieUseCase(movieId).onEach {
            when(it){
                is Resource.Error -> {
                    _state.value=MoviesDetailsViewState(error = it.message?:"An unexpected error occored")

                }
                is Resource.Loading -> {
                    _state.value=MoviesDetailsViewState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value=MoviesDetailsViewState(movie = it.data)

                }
            }
        }.launchIn(viewModelScope)
    }
}