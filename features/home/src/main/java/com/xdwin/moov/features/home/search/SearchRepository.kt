package com.xdwin.moov.features.home.search

import com.xdwin.data.data.Movies
import retrofit2.Response

interface SearchRepository  {
    suspend fun searchMovies(query: String, page: Int): Response<Movies>
}