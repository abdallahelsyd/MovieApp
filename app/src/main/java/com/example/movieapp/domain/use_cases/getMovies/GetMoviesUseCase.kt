package com.example.movieapp.domain.use_cases.getMovies

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movieapp.common.Resource
import com.example.movieapp.data.remote.MoviesAPI
import com.example.movieapp.domain.models.MoviesResponse
import com.example.movieapp.domain.reposirory.MovieRepository
import com.example.movieapp.domain.reposirory.MoviesPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MovieRepository,
    private val api: MoviesAPI
){

    operator fun invoke(): Flow<Resource<Pager<Int, MoviesResponse.Result>>> = flow {
        try {
            emit(Resource.Loading<Pager<Int, MoviesResponse.Result>>())
            //val movies=repository.getMovies()
            val pager = Pager(
                PagingConfig(pageSize = 10)) {
                MoviesPagingSource(api)
            }
            emit(Resource.Success<Pager<Int, MoviesResponse.Result>>(pager))
        }catch (e:HttpException){
            emit(Resource.Error<Pager<Int, MoviesResponse.Result>>(e.localizedMessage?:"An unexpected error occured "))
        }catch (e:IOException){
            emit(Resource.Error<Pager<Int, MoviesResponse.Result>>("Couldn't reach server. check your internet "))
        }
    }
}