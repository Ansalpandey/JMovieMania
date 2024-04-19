package com.example.mad.uilayer.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad.common.Resource
import com.example.mad.datalayer.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class MovieDetailViewModel
@Inject
constructor(private val movieRepository: MovieRepository, savedStateHandle: SavedStateHandle) :
  ViewModel() {
  val movieDetailsStateHolder = mutableStateOf(MovieDetailStateHolder())

  init {
      movieDetailsStateHolder.value = MovieDetailStateHolder(isLoading = true)
    viewModelScope.launch {
      savedStateHandle.getStateFlow("id", "0").collectLatest { getMovieDetails(it) }
    }
  }

  fun getMovieDetails(id: String) =
    viewModelScope.launch {
      when (val result = movieRepository.getMovieDetails(id)) {
        is Resource.Error -> {
          movieDetailsStateHolder.value = MovieDetailStateHolder(error = result.message.toString())
        }
        is Resource.Success -> {
          movieDetailsStateHolder.value = MovieDetailStateHolder(data = result.data)
        }
        else -> {}
      }
    }
}
