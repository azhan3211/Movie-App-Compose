package com.mizani.movieapp.ui.screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.mizani.movieapp.data.model.dto.MovieDto
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {

    private val _movieDetail = mutableStateOf(MovieDto())
    val movieDetail: State<MovieDto> = _movieDetail

    fun setMovieDetail(movieDto: MovieDto) {
        _movieDetail.value = movieDto
    }

}