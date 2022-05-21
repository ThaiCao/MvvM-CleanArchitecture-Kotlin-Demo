package com.example.mydemo.data.datasource.movie.remote

import com.example.mydemo.data.datasource.movie.IMovieDataStore
import com.example.mydemo.data.datasource.movie.local.sharedpreference.MovieSharedPreferenceDataStore
import com.example.mydemo.data.models.MovieEntity
import kotlinx.coroutines.flow.Flow

class MovieRemoteDataStore(
    private val movieRemote: IMovieRemoteStore,
    private val movieSharedPreference: MovieSharedPreferenceDataStore,
) : IMovieDataStore {
    override suspend fun getPopularsMovies(): List<MovieEntity> {
        return movieRemote.getPopularsMovies()
    }

    override suspend fun saveMovies(listMovies: List<MovieEntity>) {
        throw UnsupportedOperationException("save movies action not applicable for remote.")
    }

    override fun saveMovieExpiredTime(expiredTime: Long) {
        movieSharedPreference.saveMovieCacheExpiredTime(expiredTime)
    }

    override fun getMovieExpiredTime(): Long? {
        return movieSharedPreference.getMovieCacheExpiredTime()
    }
}