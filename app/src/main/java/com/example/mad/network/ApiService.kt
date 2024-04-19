package com.example.mad.network

import com.example.mad.model.MovieListResponse
import com.example.mad.model.details.MovieDetails
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

  @GET("3/movie/popular")
  suspend fun getMovieList(@Query("api_key") apiKey: String): MovieListResponse

  @GET("3/movie/{id}")
  suspend fun getMovieDetails(
    @Path("id") id: String,
    @Query("api_key") apiKey: String,
  ): MovieDetails
}
