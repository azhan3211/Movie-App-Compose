package com.mizani.movieapp.data.repository

import com.mizani.movieapp.data.MovieMapper
import com.mizani.movieapp.data.model.MovieType
import com.mizani.movieapp.data.model.dto.MovieDto
import com.mizani.movieapp.data.model.dto.ReviewDto
import com.mizani.movieapp.data.model.local.MovieFavoriteDao
import com.mizani.movieapp.webservice.MovieWebService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieWebService,
    private val movieFavoriteDao: MovieFavoriteDao
) : MovieRepository  {

    override fun getMovie(type: MovieType): Flow<List<MovieDto>> = flow {
        val result = movieService.getMovie(type.value).results
        emit(result.map { MovieMapper.mapMovieResponseToDto(it) })
    }

    override fun getReview(movieId: Int): Flow<List<ReviewDto>> = flow {
        val result = movieService.getReview(movieId).results
        val mapper = result.map { MovieMapper.mapReviewResponseToDto(it) }
        emit(mapper)
    }

    override suspend fun getMovieFavorite(id: Int): MovieDto {
        return try {
            MovieMapper.mapMovieEntityToDto(movieFavoriteDao.getById(id))
        } catch (e: Exception) {
            MovieDto()
        }
    }

    override fun getMovieFavoriteAll(): Flow<List<MovieDto>> = flow {
        val result = movieFavoriteDao.getAll().map {
            MovieMapper.mapMovieEntityToDto(
                it
            )
        }
        emit(result)
    }

    override suspend fun addFavorite(movieDto: MovieDto) {
        movieFavoriteDao.insert(MovieMapper.mapMovieDtoToEntity(movieDto))
    }

    override suspend fun removeFavorite(id: Int) {
        movieFavoriteDao.delete(id)
    }


}