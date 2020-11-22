package com.xdwin.detail.detail

import com.xdwin.data.api.ApiCreator
import com.xdwin.data.api.service.MovieService
import com.xdwin.data.data.Credits
import com.xdwin.data.data.Movie
import com.xdwin.data.data.MovieDetail
import retrofit2.Response
import retrofit2.awaitResponse
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor() : DetailRepository {
    override suspend fun getMovieDetail(movieId: Int): Response<MovieDetail> {
        return ApiCreator.retrofit
            .create(MovieService::class.java)
            .getMovie(movieId = movieId)
            .awaitResponse()
    }

    override suspend fun getCredits(movieId: Int): Response<Credits> {
        return ApiCreator.retrofit
            .create(MovieService::class.java)
            .getCredits(movieId)
            .awaitResponse()
    }
}