package com.example.movieapp.data.remote

import com.example.movieapp.domain.models.MovieResponse
import com.example.movieapp.domain.models.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesAPI {

    @GET("/now_playing")
    suspend fun getMovies():MoviesResponse


    @GET("/{movieId}")
    suspend fun getMovieById(@Path("movieId") movieId:String): MovieResponse
}