package com.example.mad.uilayer.movielist

import com.example.mad.model.Movie

data class MovieStateHolder(
    val isLoading: Boolean = false,
    val data: List<Movie>? = null,
    val error: String = ""
)
