package com.xdwin.data.api.service

import com.xdwin.moov.libraries.data.BuildConfig
import com.xdwin.data.data.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("search/movie")
    fun searchMovies(@Query("api_key") apiKey: String = BuildConfig.MOVIE_API_KEY,
                    @Query("query") query: String,
                    @Query("page") page: Int = 1): Call<Movies>
}