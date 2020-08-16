package com.xdwin.data.api.service

import com.xdwin.data.BuildConfig
import com.xdwin.data.data.Movie
import com.xdwin.data.data.MovieDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("movie/{movie_id}")
    fun getMovie(@Path("movie_id") movieId: Int,
                 @Query("api_key") apiKey: String = BuildConfig.MOVIE_API_KEY
    ): Call<MovieDetail>
}