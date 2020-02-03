package com.xdwin.detail.detail

import com.xdwin.data.data.Movie
import retrofit2.Response

class DetailUseCase(val repository: DetailRepository) {
    suspend fun getMovieDetail(movieId: Int): Response<Movie> {
        return repository.getMovieDetail(movieId)
    }
}