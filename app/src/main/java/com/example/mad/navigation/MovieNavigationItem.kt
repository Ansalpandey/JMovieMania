package com.example.mad.navigation

sealed class MovieNavigationItem(val route: String) {
    data object MovieList: MovieNavigationItem("movie_list")
    data object MovieDetails: MovieNavigationItem("movie_details")
}