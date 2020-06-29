package com.xdwin.common.viewmodel

import com.xdwin.data.api.ApiCreator
import com.xdwin.data.api.service.MoviesService
import com.xdwin.data.data.Movies
import retrofit2.Response
import retrofit2.awaitResponse
import javax.inject.Inject

class HomeRepository @Inject constructor() {
    suspend fun getPopularMoviesApi(): Response<Movies> {
        return ApiCreator.retrofit
            .create(MoviesService::class.java)
            .getPopularMovies(page = 1)
            .awaitResponse()
    }

    suspend fun getNowPlayingMoviesApi(): Response<Movies> {
        return ApiCreator.retrofit
            .create(MoviesService::class.java)
            .getNowPlayingMovies(page = 1)
            .awaitResponse()
    }

    suspend fun getTopRatedMoviesApi(): Response<Movies> {
        return ApiCreator.retrofit
            .create(MoviesService::class.java)
            .getTopRatedMovies(page = 1)
            .awaitResponse()
    }
}