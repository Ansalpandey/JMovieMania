package com.example.mad.datalayer

import com.example.mad.network.ApiService

class MovieDataSource(private val apiService: ApiService) {
  private val apiKey = "4687f684ff89e97131ea7aa5e2377a4b"

  suspend fun getMovieList() = apiService.getMovieList(apiKey = apiKey)
  suspend fun getMovieDetails(id: String) = apiService.getMovieDetails(id = id, apiKey = apiKey)
}
