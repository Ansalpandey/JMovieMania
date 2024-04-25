package com.example.mad.uilayer.movielist

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad.common.Resource
import com.example.mad.datalayer.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository) :
  ViewModel() {
  val movieList = mutableStateOf(MovieStateHolder())

  init {
    movieList.value = MovieStateHolder(isLoading = true)
    getMovieList()
  }

  fun getMovieList() =
    viewModelScope.launch(Dispatchers.IO) {
      when (val result = movieRepository.getMovieList()) {
        is Resource.Success -> {
          movieList.value = MovieStateHolder(data = result.data)
        }
        is Resource.Error -> {
          movieList.value = MovieStateHolder(error = result.message.toString())
        }
        else -> {

        }
      }
    }
}
