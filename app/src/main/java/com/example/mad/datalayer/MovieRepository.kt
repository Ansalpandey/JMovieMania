package com.example.mad.datalayer

import com.example.mad.common.Resource
import com.example.mad.model.Movie
import com.example.mad.model.details.MovieDetails
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieDataSource: MovieDataSource) {

  suspend fun getMovieList(): Resource<List<Movie>> {
    return try {
        Resource.Success(data = movieDataSource.getMovieList().results)
    } catch (e: Exception) {
      Resource.Error(message = e.message.toString())
    }
  }

  suspend fun getMovieDetails(id: String): Resource<MovieDetails> {
    return try {
      Resource.Success(data = movieDataSource.getMovieDetails(id))
    }
    catch (e: Exception){
      Resource.Error(
        message = e.message.toString()
      )
    }
  }
}
