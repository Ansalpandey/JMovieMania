package com.example.mad.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mad.uilayer.details.MovieDetailsScreen
import com.example.mad.uilayer.MovieListScreen

@Composable
fun MovieNavigation(navHostController: NavHostController) {
  NavHost(
    navController = navHostController,
    startDestination = MovieNavigationItem.MovieList.route,
  ) {
    composable(route = MovieNavigationItem.MovieList.route) { MovieListScreen(navHostController) }
    composable(route = MovieNavigationItem.MovieDetails.route+"/{id}") {
      val id = it.arguments?.getString("id")
      MovieDetailsScreen() }
  }
}
