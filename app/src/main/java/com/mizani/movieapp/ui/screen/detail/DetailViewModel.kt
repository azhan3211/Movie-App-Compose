package com.mizani.movieapp.ui.screen.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mizani.movieapp.data.model.dto.MovieDto
import com.mizani.movieapp.data.model.dto.ReviewDto
import com.mizani.movieapp.data.repository.MovieRepository
import com.mizani.movieapp.ui.screen.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _isFavorite = mutableStateOf(false)

    private val _reviewsState =
        mutableStateOf<ViewState<List<ReviewDto>, Exception>>(ViewState.Loading)

    var isLoaded = false
    val isFavorite: State<Boolean> = _isFavorite
    val reviewState: State<ViewState<List<ReviewDto>, Exception>> = _reviewsState

    fun getReview(movieId: Int) {
        _reviewsState.value = ViewState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                movieRepository.getReview(movieId).collect {
                    isLoaded = true
                    _reviewsState.value = ViewState.Success(it)
                }
            } catch (e: Exception) {
                _reviewsState.value = ViewState.Error(e)
            }
        }
    }

    fun addFavorite(movieDto: MovieDto) {
        viewModelScope.launch(Dispatchers.IO) {
            setFavorite(true)
            movieRepository.addFavorite(movieDto)
        }
    }

    fun removeFavorite(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            setFavorite(false)
            movieRepository.removeFavorite(id)
        }
    }

    fun isFavorite(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = movieRepository.getMovieFavorite(id)
            setFavorite(data.id != 0)
        }
    }

    private fun setFavorite(value: Boolean) {
        _isFavorite.value = value
    }

}