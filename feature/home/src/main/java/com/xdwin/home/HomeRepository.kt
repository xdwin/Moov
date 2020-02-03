package com.xdwin.home

import com.xdwin.data.api.ApiCreator
import com.xdwin.data.api.service.MoviesService
import com.xdwin.data.data.Movies
import retrofit2.Response
import retrofit2.awaitResponse

class HomeRepository {
    suspend fun getPopularMoviesApi(): Response<Movies> {
        return ApiCreator.retrofit
            .create(MoviesService::class.java)
            .getTopRatedMovies(page = 1)
            .awaitResponse()
    }
}