package com.mizani.movieapp.data.model.dto

import java.io.Serializable

data class MovieDto(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val rating: Double = 0.0,
    val posterImage: String = "",
    val thumbnailImage: String = "",
    val releaseDate: String = ""
): Serializable