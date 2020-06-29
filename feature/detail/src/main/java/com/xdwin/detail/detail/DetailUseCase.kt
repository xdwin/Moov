package com.xdwin.detail.detail

import com.xdwin.data.data.Movie
import retrofit2.Response
import javax.inject.Inject

class DetailUseCase @Inject constructor(val repository: DetailRepository) {
    suspend fun getMovieDetail(movieId: Int): Response<Movie> {
        return repository.getMovieDetail(movieId)
    }
}