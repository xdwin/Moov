package com.xdwin.home

import com.xdwin.data.data.Movies
import retrofit2.Response

class HomeUseCase(val repository: HomeRepository) {
    suspend fun getPopularMovies(): Response<Movies> {
        return repository.getPopularMoviesApi()
    }
}