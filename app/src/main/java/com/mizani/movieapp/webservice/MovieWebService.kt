package com.mizani.movieapp.webservice

import com.mizani.movieapp.data.model.remote.MovieResponse
import com.mizani.movieapp.data.model.remote.SuccessResponse
import com.mizani.movieapp.data.model.remote.review.ReviewResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieWebService {

    @GET("movie/{type}")
    suspend fun getMovie(
        @Path("type")
        type: String,
        @Query("page")
        page: Int = 1,
        @Query("language")
        language: String = "en-US"
    ): SuccessResponse<List<MovieResponse>>

    @GET("movie/{id}/reviews")
    suspend fun getReview(
        @Path("id")
        id: Int,
        @Query("page")
        page: Int = 1,
        @Query("language")
        language: String = "en-US"
    ): SuccessResponse<List<ReviewResponse>>

}