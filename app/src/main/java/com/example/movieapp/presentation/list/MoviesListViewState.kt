package com.example.movieapp.presentation.list

import androidx.paging.PagingData
import com.example.movieapp.domain.models.MoviesResponse
import kotlinx.coroutines.flow.Flow

data class MoviesListViewState(
    val isLoading:Boolean=false,
    val movies:Flow<PagingData<MoviesResponse.Result>>?=null,
    val error:String=""
)
