package com.xdwin.moov.features.detail.detail

import com.xdwin.data.api.ApiCreator
import com.xdwin.data.api.service.MovieService
import com.xdwin.data.data.Credits
import com.xdwin.data.data.MovieDetail
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.awaitResponse
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(val retrofit: Retrofit) : DetailRepository {

    override suspend fun getMovieDetail(movieId: Int): Response<MovieDetail> {
        return retrofit
            .create(MovieService::class.java)
            .getMovie(movieId = movieId)
            .awaitResponse()
    }

    override suspend fun getCredits(movieId: Int): Response<Credits> {
        return retrofit
            .create(MovieService::class.java)
            .getCredits(movieId)
            .awaitResponse()
    }
}