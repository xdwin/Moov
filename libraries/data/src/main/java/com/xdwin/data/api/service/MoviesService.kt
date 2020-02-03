package com.xdwin.data.api.service

import com.xdwin.data.BuildConfig
import com.xdwin.data.data.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {
    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String = BuildConfig.MOVIE_API_KEY,
                          @Query("page") page: Int,
                          @Query("language") language: String = "en-US"): Call<Movies>

    @GET("movie/now_playing")
    fun getNowPlayingMovies(@Query("api_key") apiKey: String = BuildConfig.MOVIE_API_KEY,
                            @Query("page") page: Int,
                            @Query("language") language: String = "en-US"): Call<Movies>

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String = BuildConfig.MOVIE_API_KEY,
                         @Query("page") page: Int,
                         @Query("language") language: String = "en-US"): Call<Movies>
}