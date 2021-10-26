package com.example.movieapp.presentation.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.movieapp.common.Resource
import com.example.movieapp.data.remote.MoviesAPI
import com.example.movieapp.domain.reposirory.MoviesPagingSource
import com.example.movieapp.domain.use_cases.getMovies.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    api:MoviesAPI
):ViewModel() {

    private val _state= mutableStateOf(MoviesListViewState())
    val state: State<MoviesListViewState> =_state

    init {
        getMovies()
    }
    val movie= Pager(
        PagingConfig(pageSize = 10)
    ) {
        MoviesPagingSource(api)
    }.flow.cachedIn(viewModelScope)

    private fun getMovies(){
        getMoviesUseCase().onEach {
            when(it){
                is Resource.Error -> {
                    _state.value=MoviesListViewState(error = it.message?:"An unexpected error occored")

                }
                is Resource.Loading -> {
                    _state.value=MoviesListViewState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value=MoviesListViewState(movies = it.data!!.flow.cachedIn(viewModelScope))

                }
            }
        }.launchIn(viewModelScope)
    }
}