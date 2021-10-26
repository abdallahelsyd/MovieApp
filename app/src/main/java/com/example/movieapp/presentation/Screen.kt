package com.example.movieapp.presentation

sealed class Screen(val route: String) {
    object MoviesListScreen: Screen("movies_list_screen")
    object MovieDetailScreen: Screen("movie_detail_screen")
}