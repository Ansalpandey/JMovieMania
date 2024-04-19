package com.example.mad.uilayer.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@Composable
fun MovieDetailsScreen(viewModel: MovieDetailViewModel = hiltViewModel()) {

  val result = viewModel.movieDetailsStateHolder.value

  if (result.isLoading) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
      CircularProgressIndicator()
    }
  }
  if (result.error.isNotBlank()) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
      Text(text = result.error)
    }
  }
  result.data?.let {
    LazyColumn(
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = Modifier.padding(12.dp),
    ) {
      item {
        AsyncImage(
          model = "https://image.tmdb.org/t/p/w500/${it.poster_path}",
          contentDescription = "movie_poster",
          modifier = Modifier.fillMaxWidth().height(600.dp).clip(shape = RoundedCornerShape(20.dp)),
          contentScale = ContentScale.FillBounds,
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
          text = it.original_title,
          fontSize = 32.sp,
          fontWeight = FontWeight.Bold,
          lineHeight = 32.sp,
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = it.tagline, fontSize = 24.sp, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(12.dp))
        Text(
          text = it.overview,
          fontSize = 20.sp,
          fontWeight = FontWeight.ExtraLight,
          textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Release Date:- ${it.release_date}")

        Spacer(modifier = Modifier.height(12.dp))
        Column {
          it.spoken_languages.forEach { spokenLanguage ->
            Text(text = "Language:- ${spokenLanguage.name}", fontSize = 22.sp)
          }
        }

        Spacer(modifier = Modifier.height(12.dp))
        val genreNames = it.genres.map { it.name }
        Text(
          text = "Genres:- ${genreNames.joinToString()}",
          maxLines = 2,
          textAlign = TextAlign.Center,
          overflow = TextOverflow.Visible,
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Revenue:- $${it.revenue}")

        Spacer(modifier = Modifier.height(12.dp))
        it.belongs_to_collection?.let { it1 -> Text(text = it1.name) }
      }
    }
  }
}
