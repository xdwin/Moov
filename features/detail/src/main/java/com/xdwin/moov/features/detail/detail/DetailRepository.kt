package com.xdwin.moov.features.detail.detail

import com.xdwin.data.data.Credits
import com.xdwin.data.data.MovieDetail
import retrofit2.Response

interface DetailRepository {
    suspend fun getMovieDetail(movieId: Int): Response<MovieDetail>
    suspend fun getCredits(movieId: Int): Response<Credits>
}