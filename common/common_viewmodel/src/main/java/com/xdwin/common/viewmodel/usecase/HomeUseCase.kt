package com.xdwin.common.viewmodel.usecase

import com.xdwin.common.viewmodel.HomeRepository
import com.xdwin.data.data.Movies
import retrofit2.Response
import javax.inject.Inject

class HomeUseCase @Inject constructor(val repository: HomeRepository) {

    suspend fun getPopularMovies(): Response<Movies> {
        return repository.getPopularMoviesApi()
    }

    suspend fun getTopRatedMovies(): Response<Movies> {
        return repository.getTopRatedMoviesApi()
    }

    suspend fun getNowPlayingMovies(): Response<Movies> {
        return repository.getNowPlayingMoviesApi()
    }
}