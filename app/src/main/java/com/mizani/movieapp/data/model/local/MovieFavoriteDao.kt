package com.mizani.movieapp.data.model.local

import androidx.room.*
@Dao
interface MovieFavoriteDao {

    @Insert
    fun insert(movieEntity: MovieEntity);

    @Query("SELECT * FROM movie_favorite_tb where id = :id")
    fun getById(id: Int): MovieEntity

    @Query("SELECT * FROM movie_favorite_tb")
    fun getAll(): List<MovieEntity>

    @Query("DELETE FROM movie_favorite_tb WHERE id = :id")
    fun delete(id: Int)

}