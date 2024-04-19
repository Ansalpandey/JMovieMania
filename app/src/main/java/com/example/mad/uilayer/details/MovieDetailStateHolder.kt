package com.example.mad.uilayer.details

import com.example.mad.model.details.MovieDetails

data class MovieDetailStateHolder(
  val isLoading: Boolean = false,
  val data: MovieDetails? = null,
  val error: String = "",
)
