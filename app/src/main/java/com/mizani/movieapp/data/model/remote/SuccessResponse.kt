package com.mizani.movieapp.data.model.remote

import com.google.gson.annotations.SerializedName

data class SuccessResponse<T>(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: T,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)