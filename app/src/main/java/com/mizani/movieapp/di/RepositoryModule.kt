package com.mizani.movieapp.di

import com.mizani.movieapp.data.repository.MovieRepository
import com.mizani.movieapp.data.repository.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun provideMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository

}