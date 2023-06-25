package com.mizani.movieapp.data.model.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        MovieEntity::class
    ],
    version = 1
)
abstract class MovieRoom() : RoomDatabase() {

    abstract fun getMovieFavoriteDao(): MovieFavoriteDao

}