package com.mizani.movieapp.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity("movie_favorite_tb")
data class MovieEntity(
    @PrimaryKey
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("poster_image")
    val posterImage: String,
    @SerializedName("thumbnail_image")
    val thumbnailImage: String,
    @SerializedName("release_date")
    val releaseDate: String
): Serializable