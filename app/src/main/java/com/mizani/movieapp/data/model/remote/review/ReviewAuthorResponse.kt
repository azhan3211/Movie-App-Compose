package com.mizani.movieapp.data.model.remote.review

import com.google.gson.annotations.SerializedName

data class ReviewAuthorResponse(
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("username")
    val username: String? = "",
    @SerializedName("avatar_path")
    val avatarPath: String? = null,
    @SerializedName("rating")
    val rating: Double = 0.0
)
