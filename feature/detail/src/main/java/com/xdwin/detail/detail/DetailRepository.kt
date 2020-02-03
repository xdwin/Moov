package com.xdwin.detail.detail

import com.xdwin.data.api.ApiCreator
import com.xdwin.data.api.service.MovieService
import com.xdwin.data.data.Movie
import retrofit2.Response
import retrofit2.awaitResponse

class DetailRepository {
    suspend fun getMovieDetail(movieId: Int): Response<Movie> {
        return ApiCreator.retrofit
            .create(MovieService::class.java)
            .getMovie(movieId = movieId)
            .awaitResponse()
    }
}