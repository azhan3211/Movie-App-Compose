package com.mizani.movieapp.di

import android.content.Context
import androidx.room.Room
import com.mizani.movieapp.data.model.local.MovieFavoriteDao
import com.mizani.movieapp.data.model.local.MovieRoom
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideNewsDao(newsRoom: MovieRoom): MovieFavoriteDao {
        return newsRoom.getMovieFavoriteDao()
    }

    @Provides
    @Singleton
    fun provideNewsRoom(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        MovieRoom::class.java,
        "movie_mizani_db"
    ).build()


}