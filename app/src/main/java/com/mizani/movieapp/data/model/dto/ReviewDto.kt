package com.mizani.movieapp.data.model.dto

import java.io.Serializable

data class ReviewDto(
    val name: String = "",
    val review: String = "",
    val rating: Double = 0.0,
    val avatar: String = ""
): Serializable