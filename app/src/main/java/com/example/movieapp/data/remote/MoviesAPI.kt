package com.example.movieapp.data.remote

import com.example.movieapp.domain.models.MovieResponse
import com.example.movieapp.domain.models.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesAPI {

    @GET("3/movie/now_playing")
    suspend fun getMovies(
        @Query("perPage") perPage:Int,
        @Query("page") page:Int,
        @Query("api_key") key:String="ebea8cfca72fdff8d2624ad7bbf78e4c"
    ):MoviesResponse


    @GET("3/movie/{movieId}")
    suspend fun getMovieById(
        @Path("movieId") movieId:String,
        @Query("api_key") key:String="ebea8cfca72fdff8d2624ad7bbf78e4c"
    ): MovieResponse
}