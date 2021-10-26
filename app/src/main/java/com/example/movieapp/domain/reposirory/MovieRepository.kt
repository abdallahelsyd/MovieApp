package com.example.movieapp.domain.reposirory

import com.example.movieapp.domain.models.MovieResponse
import com.example.movieapp.domain.models.MoviesResponse

interface MovieRepository {
    suspend fun getMovies():MoviesResponse
    suspend fun getMovieById(movieId:String):MovieResponse
}