package com.xdwin.common.viewmodel.repository

import com.xdwin.data.data.Movies
import retrofit2.Response

interface HomeRepository {
    suspend fun getPopularMoviesApi(): Response<Movies>
    suspend fun getNowPlayingMoviesApi(): Response<Movies>
    suspend fun getTopRatedMoviesApi(): Response<Movies>
}