package com.example.movieapp.di

import com.example.movieapp.common.Constants
import com.example.movieapp.data.remote.MoviesAPI
import com.example.movieapp.data.repository.MovieRepositoryImp
import com.example.movieapp.domain.reposirory.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideMovieAPI():MoviesAPI{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviesAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api:MoviesAPI):MovieRepository{
        return MovieRepositoryImp(api)
    }

}