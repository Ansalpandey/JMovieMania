package com.example.mad.datalayer

import com.example.mad.common.Resource
import com.example.mad.model.Movie

class MovieRepository(private val movieDataSource: MovieDataSource) {

  suspend fun getMovieList(): Resource<List<Movie>> {
    return try {
        Resource.Success(data = movieDataSource.getMovieList().results)
    } catch (e: Exception) {
      Resource.Error(message = e.message.toString())
    }
  }
}
