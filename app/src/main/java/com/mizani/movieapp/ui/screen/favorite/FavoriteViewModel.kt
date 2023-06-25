package com.mizani.movieapp.ui.screen.favorite

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mizani.movieapp.data.model.dto.MovieDto
import com.mizani.movieapp.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val FAVORITE = "favorite"

    private val _movies = savedStateHandle.getStateFlow(FAVORITE, emptyList<MovieDto>())
    val movies: StateFlow<List<MovieDto>> = _movies

    fun getMovieFavoriteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.getMovieFavoriteAll().collect {
                savedStateHandle[FAVORITE] = it
            }
        }
    }

}