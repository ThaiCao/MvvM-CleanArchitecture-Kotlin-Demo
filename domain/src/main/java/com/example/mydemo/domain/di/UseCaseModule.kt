package com.example.mydemo.domain.di

import com.example.mydemo.domain.dispatcher.DispatcherProvider
import com.example.mydemo.domain.dispatcher.DispatcherProviderImpl
import com.example.mydemo.domain.usecases.features.movie.IGetMoviePopularUseCase
import com.example.mydemo.domain.usecases.features.movie.GetMoviePopularUseCaseImpl
import com.example.mydemo.domain.usecases.features.movie.GetMoviePopularWithCacheUseCaseImpl
import com.example.mydemo.domain.usecases.features.movie.IGetMoviePopularWithCacheUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single<DispatcherProvider> {DispatcherProviderImpl()}

    factory<IGetMoviePopularUseCase>{
        GetMoviePopularUseCaseImpl(
            movieRepository = get()
        )
    }

    factory<IGetMoviePopularWithCacheUseCase>{
        GetMoviePopularWithCacheUseCaseImpl(
            movieRepository = get()
        )
    }
}
