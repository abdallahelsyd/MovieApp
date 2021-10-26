package com.example.movieapp.presentation.details

import com.example.movieapp.domain.models.MovieResponse
import com.example.movieapp.domain.models.MoviesResponse

data class MoviesDetailsViewState(
    val isLoading:Boolean=false,
    val movie:MovieResponse? =null,
    val error:String=""
)
