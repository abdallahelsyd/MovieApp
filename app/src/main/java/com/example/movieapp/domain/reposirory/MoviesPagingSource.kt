package com.example.movieapp.domain.reposirory

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.data.remote.MoviesAPI
import com.example.movieapp.domain.models.MoviesResponse
import retrofit2.HttpException
import java.io.IOException

class MoviesPagingSource(private val api:MoviesAPI): PagingSource<Int, MoviesResponse.Result>() {

    override fun getRefreshKey(state: PagingState<Int, MoviesResponse.Result>): Int?
    {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>):LoadResult<Int, MoviesResponse.Result> {
        return try {
            val nextPage = params.key ?: 1
            val moviesList = RetrofitClient.apiService.getMovies(10,nextPage)
            LoadResult.Page(
                data = moviesList.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (moviesList.results.isEmpty()) null else moviesList.page?.plus(1)
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}