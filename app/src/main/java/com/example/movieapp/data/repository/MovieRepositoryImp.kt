package com.example.movieapp.data.repository

import com.example.movieapp.data.remote.MoviesAPI
import com.example.movieapp.domain.models.MovieResponse
import com.example.movieapp.domain.models.MoviesResponse
import com.example.movieapp.domain.reposirory.MovieRepository
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val moviesApi:MoviesAPI
):MovieRepository{
    override suspend fun getMovies(): MoviesResponse {
        return moviesApi.getMovies()
    }

    override suspend fun getMovieById(movieID:String): MovieResponse {
        return moviesApi.getMovieById(movieID)
    }

}