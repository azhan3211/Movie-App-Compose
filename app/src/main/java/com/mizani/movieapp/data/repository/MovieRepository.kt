package com.mizani.movieapp.data.repository

import com.mizani.movieapp.data.model.MovieType
import com.mizani.movieapp.data.model.dto.MovieDto
import com.mizani.movieapp.data.model.dto.ReviewDto
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMovie(type: MovieType): Flow<List<MovieDto>>
    fun getReview(movieId: Int): Flow<List<ReviewDto>>
    fun getMovieFavoriteAll(): Flow<List<MovieDto>>
    suspend fun getMovieFavorite(id: Int): MovieDto
    suspend fun addFavorite(movieDto: MovieDto)
    suspend fun removeFavorite(id: Int)

}