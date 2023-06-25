package com.mizani.movieapp.data

import com.mizani.movieapp.data.model.dto.MovieDto
import com.mizani.movieapp.data.model.dto.ReviewDto
import com.mizani.movieapp.data.model.local.MovieEntity
import com.mizani.movieapp.data.model.remote.MovieResponse
import com.mizani.movieapp.data.model.remote.review.ReviewResponse

object MovieMapper {

    fun mapMovieResponseToDto(movies: MovieResponse) = MovieDto(
        id = movies.id,
        title = movies.title.orEmpty(),
        description = movies.description.orEmpty(),
        thumbnailImage = movies.thumbnailImage.orEmpty(),
        posterImage = movies.posterImage.orEmpty(),
        rating = movies.rate,
        releaseDate = movies.releaseData.orEmpty()
    )

    fun mapReviewResponseToDto(res: ReviewResponse) = ReviewDto(
        name = res.authorDetails.username.orEmpty(),
        review = res.content.orEmpty(),
        rating = res.authorDetails.rating,
        avatar = res.authorDetails.avatarPath.orEmpty()
    )

    fun mapMovieDtoToEntity(movieDto: MovieDto) = MovieEntity(
        id = movieDto.id,
        title = movieDto.title,
        posterImage = movieDto.posterImage,
        rating = movieDto.rating,
        releaseDate = movieDto.releaseDate,
        thumbnailImage = movieDto.thumbnailImage,
        description = movieDto.description
    )
    
    fun mapMovieEntityToDto(movieEntity: MovieEntity) = MovieDto(
        id = movieEntity.id,
        title = movieEntity.title,
        posterImage = movieEntity.posterImage,
        rating = movieEntity.rating,
        releaseDate = movieEntity.releaseDate,
        thumbnailImage = movieEntity.thumbnailImage,
        description = movieEntity.description
    )

}