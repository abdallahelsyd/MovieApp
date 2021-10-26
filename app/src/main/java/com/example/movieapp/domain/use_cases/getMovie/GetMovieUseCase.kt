package com.example.movieapp.domain.use_cases.getMovie

import com.example.movieapp.common.Resource
import com.example.movieapp.domain.models.MovieResponse
import com.example.movieapp.domain.models.MoviesResponse
import com.example.movieapp.domain.reposirory.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val repository: MovieRepository
){

    operator fun invoke(movieId:String): Flow<Resource<MovieResponse>> = flow {
        try {
            emit(Resource.Loading<MovieResponse>())
            val movie=repository.getMovieById(movieId)
            emit(Resource.Success<MovieResponse>(movie))
        }catch (e:HttpException){
            emit(Resource.Error<MovieResponse>(e.localizedMessage?:"An unexpected error occured "))
        }catch (e:IOException){
            emit(Resource.Error<MovieResponse>("Couldn't reach server. check your internet "))
        }
    }
}