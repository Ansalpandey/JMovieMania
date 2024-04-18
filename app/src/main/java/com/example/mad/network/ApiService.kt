package com.example.mad.network

import com.example.mad.model.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("3/movie/popular")
    suspend fun getMovieList(@Query("api_key") apiKey :String) : MovieListResponse
}