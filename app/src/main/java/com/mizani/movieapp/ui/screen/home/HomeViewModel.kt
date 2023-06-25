package com.mizani.movieapp.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mizani.movieapp.data.model.MovieType
import com.mizani.movieapp.data.model.dto.MovieDto
import com.mizani.movieapp.data.repository.MovieRepository
import com.mizani.movieapp.ui.screen.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _moviesPopularState =
        mutableStateOf<ViewState<List<MovieDto>, Exception>>(ViewState.Loading)
    private val _moviesNowPlayingState =
        mutableStateOf<ViewState<List<MovieDto>, Exception>>(ViewState.Loading)
    private val _moviesTopRatedState =
        mutableStateOf<ViewState<List<MovieDto>, Exception>>(ViewState.Loading)

    var isPopularLoaded = false
    var isNowPlayingLoaded = false
    var isTopRatedLoaded = false

    val moviePopular: State<ViewState<List<MovieDto>, Exception>> = _moviesPopularState
    val movieNowPlaying: State<ViewState<List<MovieDto>, Exception>> = _moviesNowPlayingState
    val movieTopRated: State<ViewState<List<MovieDto>, Exception>> = _moviesTopRatedState

    fun getMovieTopRated() {
        _moviesTopRatedState.value = ViewState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getMovie(MovieType.TOP_RATED).collect {
                    isTopRatedLoaded = true
                    _moviesTopRatedState.value = ViewState.Success(it)
                }
            } catch (e: Exception) {
                _moviesTopRatedState.value = ViewState.Error(e)
            }
        }
    }
    fun getMoviePopular() {
        _moviesPopularState.value = ViewState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getMovie(MovieType.POPULAR).collect {
                    isPopularLoaded = true
                    _moviesPopularState.value = ViewState.Success(it)
                }
            } catch (e: Exception) {
                _moviesPopularState.value = ViewState.Error(e)
            }
        }
    }
    fun getMovieNowPlaying() {
        _moviesNowPlayingState.value = ViewState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getMovie(MovieType.NOW_PLAYING).collect {
                    isNowPlayingLoaded = true
                    _moviesNowPlayingState.value = ViewState.Success(it)
                }
            } catch (e: Exception) {
                _moviesNowPlayingState.value = ViewState.Error(e)
            }
        }
    }
}