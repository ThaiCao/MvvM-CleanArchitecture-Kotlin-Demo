package com.example.mydemo.data.datasource.movie.local.db

import com.example.mydemo.data.datasource.movie.IMovieDataStore
import com.example.mydemo.data.datasource.movie.local.sharedpreference.MovieSharedPreferenceDataStore
import com.example.mydemo.data.models.MovieEntity

class MovieLocalDataStore(
    private val movieLocal: IMovieLocalStore,
    private val movieSharedPreference: MovieSharedPreferenceDataStore,
) : IMovieDataStore {
    override suspend fun getPopularsMovies(): List<MovieEntity> {
        return movieLocal.getPopularsMovies()
    }

    override suspend fun saveMovies(listMovies: List<MovieEntity>) {
       return movieLocal.saveMovies(listMovies)
    }

    override fun saveMovieExpiredTime(expiredTime: Long) {
        movieSharedPreference.saveMovieCacheExpiredTime(expiredTime)
    }

    override fun getMovieExpiredTime(): Long? {
        return movieSharedPreference.getMovieCacheExpiredTime()
    }
}