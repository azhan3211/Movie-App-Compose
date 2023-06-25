package com.mizani.movieapp.data.model.remote

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("backdrop_path")
    val thumbnailImage: String? = null,
    @SerializedName("poster_path")
    val posterImage: String? = null,
    @SerializedName("overview")
    val description: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("release_date")
    val releaseData: String? = null,
    @SerializedName("vote_average")
    val rate: Double = 0.0
)