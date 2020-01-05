package com.xdwin.base.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.xdwin.base.api.ApiCreator
import com.xdwin.base.api.service.MoviesService
import com.xdwin.base.data.Movies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object HomeRepository {
    private var popularMovies = getPopularMoviesApi()

    fun getPopularMovies(): LiveData<Movies> {
        return popularMovies
    }

    fun getPopularMoviesApi(): MutableLiveData<Movies> {
        val moviesResult = MutableLiveData<Movies>()
        ApiCreator.retrofit
            .create(MoviesService::class.java)
            .getTopRatedMovies(page = 1)
            .enqueue(object : Callback<Movies> {
                override fun onFailure(call: Call<Movies>, t: Throwable) {
                    moviesResult.value = null
                }

                override fun onResponse(
                    call: Call<Movies>,
                    response: Response<Movies>
                ) {
                    moviesResult.value = response.body()
                }

            })
        return moviesResult
    }
}