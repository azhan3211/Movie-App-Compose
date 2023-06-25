package com.mizani.movieapp.data.model.remote.review

import com.google.gson.annotations.SerializedName

data class ReviewResponse(
    @SerializedName("author")
    val author: String? = "",
    @SerializedName("author_details")
    val authorDetails: ReviewAuthorResponse,
    @SerializedName("content")
    val content: String? = "",
    @SerializedName("created_at")
    val createdAt: String? = "",
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("updated_at")
    val updatedAt: String? = "",
    @SerializedName("url")
    val url: String? = ""
)
